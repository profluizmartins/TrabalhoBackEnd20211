package br.ufg.inf.fs.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.inf.fs.entities.ProdutoEstoque;

@Repository
public interface ProdutoEstoqueRepository extends JpaRepository<ProdutoEstoque, Integer>{

	Optional<ProdutoEstoque> findByProdutoIdProduto(Integer idProduto);

	@Query("SELECT produtoEstoque FROM ProdutoEstoque produtoEstoque WHERE lower(produtoEstoque.produto.nmProduto) LIKE %:nmProduto%")
	Page<ProdutoEstoque> findAllByNmProduto(Pageable pageable, String nmProduto);
}
