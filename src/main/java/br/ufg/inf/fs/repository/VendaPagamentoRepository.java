package br.ufg.inf.fs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufg.inf.fs.entities.VendaPagamento;

public interface VendaPagamentoRepository extends JpaRepository<VendaPagamento, String> {

}
