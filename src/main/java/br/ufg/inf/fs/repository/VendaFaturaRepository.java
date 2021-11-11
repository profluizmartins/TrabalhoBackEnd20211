package br.ufg.inf.fs.repository;

import br.ufg.inf.fs.entities.VendaFatura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendaFaturaRepository extends JpaRepository<VendaFatura, String> {
    @Query("SELECT vf FROM VendaFatura vf WHERE vf.quitado = true")
    public List<VendaFatura> findFaturasPagas();

    @Query("SELECT vf FROM VendaFatura vf WHERE vf.quitado = true")
    public Page<VendaFatura> findFaturasPagas(Pageable pageable);

    @Query("SELECT vf FROM VendaFatura vf WHERE vf.quitado = false")
    public List<VendaFatura> findFaturasPendentes();

    @Query("SELECT vf FROM VendaFatura vf WHERE vf.quitado = false")
    public Page<VendaFatura> findFaturasPendentes(Pageable pageable);
}
