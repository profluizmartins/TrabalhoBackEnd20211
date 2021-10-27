package br.ufg.inf.fs.repository;

import br.ufg.inf.fs.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    //findByUsuario
}
