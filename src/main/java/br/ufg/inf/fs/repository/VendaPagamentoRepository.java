package br.ufg.inf.fs.repository;

import br.ufg.inf.fs.entities.VendaPagamento;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VendaPagamentoRepository extends JpaRepository<VendaPagamento, Integer> {

    @Query("SELECT vp FROM VendaPagamento vp WHERE month(vp.dtPagamento) = :mesReferencia")
    Page<VendaPagamento> findVendaPagamentosByMes(@Param("mesReferencia") int mesReferencia, Pageable pageable);

    @Query("SELECT vp FROM VendaPagamento vp WHERE vp.vlPagamento - vp.vendaFatura.valorVenda > 0 and month(vp.dtPagamento) = :mesReferencia")
    Page<VendaPagamento> findPagamentosAtrasadosByMes(@Param("mesReferencia") int mesReferencia, Pageable pageable);

    @Query("SELECT vp FROM VendaPagamento vp WHERE vp.vendaFatura.quitado = false and month(vp.dtPagamento) = :mesReferencia")
    Page<VendaPagamento> findPagamentosPrevisaoByMes(@Param("mesReferencia") int mesReferencia, Pageable pageable);

    @Query("SELECT vp FROM VendaPagamento vp WHERE vp.vendaFatura.quitado = false")
    Page<VendaPagamento> getPagamentosPendentes(Pageable pageable);

    @Query("SELECT vp FROM VendaPagamento vp WHERE vp.vendaFatura.quitado = false")
    List<VendaPagamento> getPagamentosPendentesAll();

    @Query("SELECT vp FROM VendaPagamento vp WHERE vp.vendaFatura.quitado = true")
    Page<VendaPagamento> getPagamentosRealizados(Pageable pageable);

}
