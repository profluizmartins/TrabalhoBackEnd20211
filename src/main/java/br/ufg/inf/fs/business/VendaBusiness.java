package br.ufg.inf.fs.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ufg.inf.fs.ctrl.dto.VendaDto;
import br.ufg.inf.fs.entities.Produto;
import br.ufg.inf.fs.entities.Venda;
import br.ufg.inf.fs.entities.VendaFatura;
import br.ufg.inf.fs.entities.VendaProduto;
import br.ufg.inf.fs.enums.TipoPagamento;
import br.ufg.inf.fs.exceptions.PessoaException;
import br.ufg.inf.fs.repository.VendaFaturaRepository;
import br.ufg.inf.fs.repository.VendaProdutoRepository;
import br.ufg.inf.fs.repository.VendaRepository;

@Service
public class VendaBusiness {

    @Autowired
    VendaRepository vendaRepository;
    
    @Autowired
    VendaProdutoRepository vendaProdutoRepository;
    
    @Autowired
    VendaFaturaRepository vendaFaturaRepository;

    //findAll
    public List<Venda> findAll() { return vendaRepository.findAll();}

    //findByID
    public Venda findByID(Integer id){
        Optional<Venda> retorno =  vendaRepository.findById(id);
        return retorno.orElse(null);
    }
    
    //findByIDComprador
    public List<Venda> findByIDComprador(Integer id){
        return vendaRepository.FindByIdComprador(id);
    }

    //insert/update
    public Venda insertUpdate(VendaDto vendadto) throws PessoaException{
        this.validaVenda(vendadto.getVenda());
        Venda venda = vendaRepository.save(vendadto.getVenda());
		for (Produto produto : vendadto.getProdutos()) {
			VendaProduto vendaProduto = new VendaProduto();
			vendaProduto.setProduto(produto);
			vendaProduto.setVenda(venda);
			vendaProduto.setPrecoVendaProduto(produto.getPrUnitario());
			vendaProduto.setQuantidade(produto.getUnidadeMedida().getId());
			vendaProdutoRepository.save(vendaProduto);
		}
		if (venda.getConcluido()) {
			for (int i = 0; i < venda.getQtdPrestacao(); i++) {
				VendaFatura fatura = new VendaFatura();
				fatura.setVenda(venda);
				fatura.setQuitado(false);
				vendaFaturaRepository.save(fatura);
			}
		}
        return venda;
    }

    //delete
    public void delete(Integer id) throws PessoaException{
    	vendaRepository.deleteById(id);
    }

    //PaginatorFindAll
    public Page<Venda> paginatorFindAll(Pageable pageable){
        return vendaRepository.findAll(pageable);
    }

    //validaVenda
    private void validaVenda(Venda venda) throws PessoaException{
        if(venda.getQtdPrestacao() < 0 || 
        	(venda.getQtdPrestacao() != 1 && (
        							venda.getTipoPagamento() == TipoPagamento.DINHEIRO ||
        							venda.getTipoPagamento() == TipoPagamento.CARTAO_DEBITO ||
        							venda.getTipoPagamento() == TipoPagamento.PIX))
        ){
            throw new PessoaException("907");
        }
    }
}
