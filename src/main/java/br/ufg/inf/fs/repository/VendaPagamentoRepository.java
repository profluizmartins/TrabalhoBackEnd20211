package br.ufg.inf.fs.repository;

import br.ufg.inf.fs.entities.VendaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendaPagamentoRepository extends JpaRepository<VendaPagamento, String> {

    @Query("SELECT vp FROM VendaPagamento vp WHERE substring(vp.dtPagamento, 1, 4) = :mesReferencia")
    List<VendaPagamento> findVendaPagamentosByMes(@Param("mesReferencia") int mesReferencia);

    @Query("SELECT vp FROM VendaPagamento vp WHERE vp.vlPagamento - vp.vendaFatura.valorVenda > 0 and substring(vp.dtPagamento, 1, 4) = :mesReferencia")
    List<VendaPagamento> findPagamentosAtrasadosByMes(@Param("mesReferencia") int mesReferencia);

    @Query("SELECT vp FROM VendaPagamento vp WHERE vp.vendaFatura.quitado = false")
    List<VendaPagamento> getPagamentosPendentes();

    @Query("SELECT vp FROM VendaPagamento vp WHERE vp.vendaFatura.quitado = true")
    List<VendaPagamento> getPagamentosRealizados();

}
