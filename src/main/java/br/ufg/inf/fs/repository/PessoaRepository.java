package br.ufg.inf.fs.repository;

import br.ufg.inf.fs.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    //findByNome
    @Query("SELECT p FROM Pessoa p WHERE p.nome LIKE %:str%")
    public List<Pessoa> findByNome(@Param("str")String str);

    //findByEndereco
    @Query("SELECT p FROM Pessoa p WHERE p.endereco LIKE %:str%")
    public List<Pessoa> findByEndereco(@Param("str")String str);
}
