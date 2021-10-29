package br.ufg.inf.fs.business;

import br.ufg.inf.fs.entities.Usuario;
import br.ufg.inf.fs.exceptions.UsuarioException;
import br.ufg.inf.fs.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioBusiness {
    @Autowired
    UsuarioRepository repository;

    //findAll
    public List<Usuario> findAll(){ return repository.findAll();}

    //findByID
    public Usuario findById(String id){
        Optional<Usuario> retorno = repository.findById(id);
        if(retorno.isPresent()){
            return retorno.get();
        }
        return null;
    }

    //insert
    public Usuario insert(Usuario usuario) throws UsuarioException{
        this.validaUsuario(usuario);
        return repository.save(usuario);
    }

    //update
    public Usuario update(Usuario usuario) throws UsuarioException{
        this.validaUsuario(usuario);
        return repository.save(usuario);
    }

    //delete
    public void delete(String id){
        repository.deleteById(id);
    }

    //Paginator
    public Page<Usuario> paginatorFindAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    //validaUsuario
    private void validaUsuario(Usuario usuario) throws UsuarioException {
        if(usuario.getLogin() == null || usuario.getLogin().length() < 0){
            throw new UsuarioException("0307");
        }

        if(usuario.getSenha() == null || usuario.getSenha().length() < 0){
            throw new UsuarioException("0308");
        }

        if(usuario.getPessoa() == null){
            throw new UsuarioException("0310");
        }
    }
}
