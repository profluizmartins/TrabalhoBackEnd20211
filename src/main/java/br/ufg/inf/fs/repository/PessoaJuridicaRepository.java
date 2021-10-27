package br.ufg.inf.fs.repository;

import br.ufg.inf.fs.entities.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaJuridicaRepository  extends JpaRepository<PessoaJuridica, Integer> {
    //findByCNPJ

    //findByAtividade
}
