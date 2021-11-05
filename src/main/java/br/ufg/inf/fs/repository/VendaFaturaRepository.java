package br.ufg.inf.fs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufg.inf.fs.entities.VendaFatura;

public interface VendaFaturaRepository extends JpaRepository<VendaFatura, String> {

}
