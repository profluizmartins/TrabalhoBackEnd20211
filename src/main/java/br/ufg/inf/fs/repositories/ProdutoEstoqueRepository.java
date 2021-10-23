package br.ufg.inf.fs.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufg.inf.fs.entities.ProdutoEstoque;

@Repository
public interface ProdutoEstoqueRepository extends JpaRepository<ProdutoEstoque, Integer>{

	Optional<ProdutoEstoque> findByProdutoIdProduto(Integer idProduto);
}
