package br.ufg.inf.fs.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ufg.inf.fs.Messages;
import br.ufg.inf.fs.entities.PessoaJuridica;
import br.ufg.inf.fs.exceptions.PessoaJuridicaException;
import br.ufg.inf.fs.repository.EntradaRepository;
import br.ufg.inf.fs.repository.PessoaJuridicaRepository;
import br.ufg.inf.fs.repository.VendaRepository;

@Service
public class PessoaJuridicaBusiness {
	
	@Autowired
	PessoaJuridicaRepository pessoaJuridicaRepository;
	
    @Autowired
    EntradaRepository entradaRepository;

    @Autowired
    VendaRepository vendaRepository;
	
	//findAll
    public List<PessoaJuridica> findAll() { return pessoaJuridicaRepository.findAll();}
    
    //findByID
    public PessoaJuridica findByID(Integer id){
        Optional<PessoaJuridica> retorno =  pessoaJuridicaRepository.findById(id);
        return retorno.orElse(null);
    }
    
    //findByAtividade
    public List<PessoaJuridica> findByAtividade(String atividade){
		return pessoaJuridicaRepository.findByAtividade(atividade);
    }
    
    //findByCNPJ
    public List<PessoaJuridica> findByCNPJ(String cnpj){
		return pessoaJuridicaRepository.findByCPNJ(cnpj);
    }
    
    //insert
    public PessoaJuridica insert(PessoaJuridica pessoaJuridica) throws PessoaJuridicaException{
        this.validaPessoaJuridica(pessoaJuridica);
        return pessoaJuridicaRepository.save(pessoaJuridica);
    }
    
    //update
    public PessoaJuridica update(PessoaJuridica pessoaJuridica) throws PessoaJuridicaException{
        this.validaPessoaJuridica(pessoaJuridica);
        return pessoaJuridicaRepository.save(pessoaJuridica);
    }
    
    //delete
    public void delete(Integer id) throws PessoaJuridicaException{
        if(entradaRepository.findByIdPessoa(id).size() == 0 && vendaRepository.FindByIdComprador(id).size() == 0){
            pessoaJuridicaRepository.deleteById(id);
        } else{
            throw new PessoaJuridicaException(Messages.get("0209"));
        }
    }
    
    //PaginatorFindAll
    public Page<PessoaJuridica> paginatorFindAll(Pageable pageable){
        return pessoaJuridicaRepository.findAll(pageable);
    }
    
    //validaPessoaJuridica
    private void validaPessoaJuridica(PessoaJuridica pessoaJuridica) throws PessoaJuridicaException{
        if(pessoaJuridica.getAtividade() == null || pessoaJuridica.getAtividade().length() == 0){
            throw new PessoaJuridicaException("0207");
        }

        if(pessoaJuridica.getCnpj() == null || String.valueOf(pessoaJuridica.getCnpj()).length() == 0){
            throw new PessoaJuridicaException("0208");
        }
    }
}
