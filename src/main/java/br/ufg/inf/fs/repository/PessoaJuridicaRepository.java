package br.ufg.inf.fs.repository;

import br.ufg.inf.fs.entities.PessoaJuridica;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PessoaJuridicaRepository  extends JpaRepository<PessoaJuridica, Integer> {

	//findByAtividade
	@Query("SELECT p FROM PessoaJuridica p WHERE p.atividade LIKE %:str%")
	public List<PessoaJuridica> findByAtividade(@Param("str")String atividade);

	//findByCNPJ
	@Query("SELECT p FROM PessoaJuridica p WHERE p.cnpj LIKE %:str%")
	public List<PessoaJuridica> findByCPNJ(@Param("str")String cnpj);
	
}
