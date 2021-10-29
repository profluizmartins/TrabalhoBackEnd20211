package br.ufg.inf.fs.business;

import br.ufg.inf.fs.Messages;
import br.ufg.inf.fs.entities.Pessoa;
import br.ufg.inf.fs.exceptions.PessoaException;
import br.ufg.inf.fs.repository.EntradaRepository;
import br.ufg.inf.fs.repository.PessoaRepository;
import br.ufg.inf.fs.repository.VendaRepository;
import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaBusiness {
    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    EntradaRepository entradaRepository;

    @Autowired
    VendaRepository vendaRepository;

    //findAll
    public List<Pessoa> findAll() { return pessoaRepository.findAll();}

    //findByID
    public Pessoa findByID(Integer id){
        Optional<Pessoa> retorno =  pessoaRepository.findById(id);
        return retorno.orElse(null);
    }

    //findByNome
    public List<Pessoa> findByNome(String nome){
        return pessoaRepository.findByNome(nome);
    }

    //findByEndereco
    public List<Pessoa> findByEndereco(String endereco){
        return pessoaRepository.findByEndereco(endereco);
    }

    //insert
    public Pessoa insert(Pessoa pessoa) throws PessoaException{
        this.validaPessoa(pessoa);
        return pessoaRepository.save(pessoa);
    }

    //update
    public Pessoa update(Pessoa pessoa) throws PessoaException{
        this.validaPessoa(pessoa);
        return pessoaRepository.save(pessoa);
    }

    //delete
    public void delete(Integer id) throws PessoaException{
        if(entradaRepository.findByIdPessoa(id).size() == 0 && vendaRepository.FindByIdComprador(id).size() == 0){
            pessoaRepository.deleteById(id);
        } else{
            throw new PessoaException(Messages.get("0109"));
        }
    }

    //PaginatorFindAll
    public Page<Pessoa> paginatorFindAll(Pageable pageable){
        return pessoaRepository.findAll(pageable);
    }

    //validaPessoa
    private void validaPessoa(Pessoa pessoa) throws PessoaException{
        if(pessoa.getNome() == null || pessoa.getNome().length() == 0){
            throw new PessoaException("0107");
        }

        if(pessoa.getEndereco() == null || pessoa.getEndereco().length() == 0){
            throw new PessoaException("0108");
        }
    }
}
