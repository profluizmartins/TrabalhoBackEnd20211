package br.ufg.inf.fs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufg.inf.fs.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
