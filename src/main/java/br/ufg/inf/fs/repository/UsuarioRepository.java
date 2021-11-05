package br.ufg.inf.fs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufg.inf.fs.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
