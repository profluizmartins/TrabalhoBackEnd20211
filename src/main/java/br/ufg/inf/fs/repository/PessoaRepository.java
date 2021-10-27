package br.ufg.inf.fs.repository;

import br.ufg.inf.fs.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    //findByNome

    //findByEndereco

    //findByUsuario
}
