package com.mfigueroa.msvc_usuarios.services;

import com.mfigueroa.msvc_usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByEmail(String email);
    Usuario save(Usuario usuario);
    void delete(long id);
    List<Usuario> findAll(List<Long> ids);
}
