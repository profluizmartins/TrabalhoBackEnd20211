package br.ufg.inf.fs.repository;

import br.ufg.inf.fs.entities.Produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	@Query("SELECT p FROM Produto p WHERE p.dsProduto LIKE %:str%")
	public List<Produto> findByDescProd(@Param("str") String str);
	
}
