package br.ufg.inf.fs.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ufg.inf.fs.entities.Produto;
import br.ufg.inf.fs.exceptions.ProdutoException;
import br.ufg.inf.fs.repository.ProdutoRepository;

@Service
public class ProdutoBusiness {
	
	@Autowired
	private ProdutoRepository repository;
	
	public List<Produto> findAll(){
		return repository.findAll();		
	}
	
	
	public Page<Produto> paginator(Pageable pageable){
		return repository.findAll(pageable);		
	}
	
	
	public List<Produto> findName(String str){
		return repository.findByDescProd(str);
	}
	
	public Produto findById(Integer id) {
		Optional<Produto> retorno = repository.findById(id);
		return retorno.get();
	}
	
	public Produto insert(Produto produto) throws ProdutoException {
		this.validaProduto(produto);
		return repository.save(produto);
	}
	
	public Produto update(Produto produto) throws ProdutoException {
		this.validaProduto(produto);
		return repository.save(produto);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	
	private void validaProduto(Produto produto) throws ProdutoException {
		if(produto.getNmProduto() == null || produto.getNmProduto().length() == 0) {
			throw new ProdutoException("0508");
		}
		if(produto.getDsProduto() == null || produto.getDsProduto().length() == 0) {
			throw new ProdutoException("0509");
		}
		if(produto.getPrUnitario() == null || produto.getPrUnitario() == 0) {
			throw new ProdutoException("0510");
		}
		if(produto.getUnidadeMedida() == null) {
			throw new ProdutoException("0511");
		}
	}
}
