package br.ufg.inf.fs.repository;

import br.ufg.inf.fs.entities.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntradaRepository extends JpaRepository<Entrada, Integer> {
    @Query("SELECT e FROM Entrada e WHERE e.fornecedor.pessoa.idPessoa = :id")
    public List<Entrada> findByIdPessoa(@Param("id") Integer id);
}
