package br.ufg.inf.fs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufg.inf.fs.entities.VendaProduto;

public interface VendaProdutoRepository extends JpaRepository<VendaProduto, Integer> {

}
