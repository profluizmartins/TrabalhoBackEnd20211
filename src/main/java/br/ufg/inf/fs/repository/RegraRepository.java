package br.ufg.inf.fs.repository;

import br.ufg.inf.fs.entities.Regra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegraRepository extends JpaRepository<Regra, String> {
}
