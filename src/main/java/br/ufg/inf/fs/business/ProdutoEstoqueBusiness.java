package br.ufg.inf.fs.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufg.inf.fs.entities.ProdutoEstoque;
import br.ufg.inf.fs.repositories.ProdutoEstoqueRepository;

@Service
public class ProdutoEstoqueBusiness {

	@Autowired
	private ProdutoEstoqueRepository repository;
	
	public Optional<ProdutoEstoque> insert(ProdutoEstoque entity) {
		ProdutoEstoque retorno = repository.save(entity);
		return Optional.of(retorno);
	}
	
	
}
