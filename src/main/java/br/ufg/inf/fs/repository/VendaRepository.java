package br.ufg.inf.fs.repository;

import br.ufg.inf.fs.entities.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Integer> {

    @Query("SELECT v FROM Venda v WHERE v.comprador.idPessoa = :id")
    public List<VendaRepository> FindByIdComprador(@Param("id")Integer id);

}
