package br.ufg.inf.fs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import br.ufg.inf.fs.entities.VendaFatura;

public interface VendaFaturaRepository extends JpaRepository<VendaFatura, String> {
    @Query("SELECT vf FROM VendaFatura vf WHERE vf.quitado = true")
    public List<VendaFatura> findFaturasPagas();
}
