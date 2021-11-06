package br.ufg.inf.fs.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ufg.inf.fs.dto.ProdutoEstoqueInsert;
import br.ufg.inf.fs.dto.ProdutoEstoqueLibera;
import br.ufg.inf.fs.dto.response.ProdutoEstoqueResponse;
import br.ufg.inf.fs.entities.ProdutoEstoque;
import br.ufg.inf.fs.repositories.ProdutoEstoqueRepository;
import br.ufg.inf.fs.repositories.ProdutoRepository;
import br.ufg.inf.fs.validations.exceptions.NotFoundException;

@Service
public class ProdutoEstoqueBusiness {
	
	@Autowired
	private ProdutoEstoqueRepository repository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public List<ProdutoEstoqueResponse> listAllProdutoEstoque() {
		List<ProdutoEstoque> produtoEstoques = repository.findAll();

		List<ProdutoEstoqueResponse> produtoEstoqueResponses = mapperProdutoEstoqueToResponse(produtoEstoques);
		return produtoEstoqueResponses;
	}
	
	private List<ProdutoEstoqueResponse> mapperProdutoEstoqueToResponse(List<ProdutoEstoque> produtoEstoques) {
		List<ProdutoEstoqueResponse> produtoEstoqueResponses = new ArrayList<ProdutoEstoqueResponse>();
		for(int i = 0; i < produtoEstoques.size(); i++) {
			ProdutoEstoqueResponse produtoEstoqueResponse = new ProdutoEstoqueResponse();
			produtoEstoqueResponse.setIdProdutoEstoque(produtoEstoques.get(i).getIdProdutoEstoque());
			produtoEstoqueResponse.setNmProduto(produtoEstoques.get(i).getProduto().getNmProduto());
			produtoEstoqueResponse.setGrupo(produtoEstoques.get(i).getProduto().getGrupo());
			produtoEstoqueResponse.setUnidadeMedida(produtoEstoques.get(i).getProduto().getUnidadeMedida());
			produtoEstoqueResponse.setPrUnitario(produtoEstoques.get(i).getProduto().getPrUnitario());
			produtoEstoqueResponse.setQtdEstoque(produtoEstoques.get(i).getQtdEstoque());
			produtoEstoqueResponse.setQtdReservada(produtoEstoques.get(i).getQtdReservada());
			produtoEstoqueResponses.add(produtoEstoqueResponse);
		}

		return produtoEstoqueResponses;
	}

	@Transactional
	public Page<ProdutoEstoqueResponse> listAllProdutoEstoqueinPageable(Pageable pageable) {
		Page<ProdutoEstoque> produtoEstoques = repository.findAll(pageable);

		Page<ProdutoEstoqueResponse> produtoEstoqueResponses = produtoEstoques.map(this::mapperPageProdutoEstoqueToResponse);
		return produtoEstoqueResponses;
	}
	
	@Transactional
	public Page<ProdutoEstoqueResponse> listAllProdutoEstoqueinPageableByNmProduto(Pageable pageable, String nmProduto) {
		Page<ProdutoEstoque> produtoEstoques = repository.findAllByNmProduto(pageable, nmProduto.toLowerCase());

		Page<ProdutoEstoqueResponse> produtoEstoqueResponses = produtoEstoques
				.map(this::mapperPageProdutoEstoqueToResponse);
		return produtoEstoqueResponses;
	}

	private ProdutoEstoqueResponse mapperPageProdutoEstoqueToResponse(ProdutoEstoque produtoEstoque) {
		ProdutoEstoqueResponse produtoEstoqueResponse = new ProdutoEstoqueResponse();
		produtoEstoqueResponse.setIdProdutoEstoque(produtoEstoque.getIdProdutoEstoque());
		produtoEstoqueResponse.setNmProduto(produtoEstoque.getProduto().getNmProduto());
		produtoEstoqueResponse.setGrupo(produtoEstoque.getProduto().getGrupo());
		produtoEstoqueResponse.setPrUnitario(produtoEstoque.getProduto().getPrUnitario());
		produtoEstoqueResponse.setUnidadeMedida(produtoEstoque.getProduto().getUnidadeMedida());
		produtoEstoqueResponse.setQtdEstoque(produtoEstoque.getQtdEstoque());
		produtoEstoqueResponse.setQtdReservada(produtoEstoque.getQtdReservada());
		return produtoEstoqueResponse;
	}

	@Transactional
	public Optional<ProdutoEstoque> insert(ProdutoEstoqueInsert dto) {
		Optional<ProdutoEstoque> possivelProdutoEstoque = repository.findByProdutoIdProduto(dto.getProduto());
		if (possivelProdutoEstoque.isEmpty()) {
			ProdutoEstoque entity = dto.toModel(produtoRepository);
			return Optional.of(repository.save(entity));
		}
		ProdutoEstoque produtoEstoque = possivelProdutoEstoque.get();
		produtoEstoque.adicionarQtdEstoque(dto.getQuantidade());
		return Optional.of(produtoEstoque);
	}
	
	@Transactional
	public Optional<ProdutoEstoque> reservarEstoque(ProdutoEstoqueInsert dto) {
		Optional<ProdutoEstoque> possivelProdutoEstoque = repository.findByProdutoIdProduto(dto.getProduto());
		if (possivelProdutoEstoque.isEmpty()) {
			throw new NotFoundException("ProdutoEstoque não foi encontrado para o Produto informado");
		}
		ProdutoEstoque produtoEstoque = possivelProdutoEstoque.get();
		produtoEstoque.adicionarQtdReservada(dto.getQuantidade());
		return Optional.of(produtoEstoque);
	}

	@Transactional
	public Optional<ProdutoEstoque> alterarReservaEstoque(ProdutoEstoqueInsert dto, Integer qtdAlterdo) {
		Optional<ProdutoEstoque> possivelProdutoEstoque = repository.findByProdutoIdProduto(dto.getProduto());
		if (possivelProdutoEstoque.isEmpty()) {
			throw new NotFoundException("ProdutoEstoque não foi encontrado para o Produto informado");
		}
		ProdutoEstoque produtoEstoque = possivelProdutoEstoque.get();
		produtoEstoque.adicionarQtdReservada(qtdAlterdo);
		return Optional.of(produtoEstoque);
	}
	
	@Transactional
	public Optional<ProdutoEstoque> liberarEstoque(ProdutoEstoqueLibera dto) {
		Optional<ProdutoEstoque> possivelProdutoEstoque = repository.findByProdutoIdProduto(dto.getProduto());
		
		if (possivelProdutoEstoque.isEmpty()) {
			throw new NotFoundException("ProdutoEstoque não foi encontrado para o Produto informado");
		}
		
		ProdutoEstoque produtoEstoque = possivelProdutoEstoque.get();
		
		produtoEstoque.removerQtdReservada(dto.getQuantidade(), dto.getIsCompra());
		return Optional.of(produtoEstoque);
	}
}
