package com.mfigueroa.msvc_usuarios.services;

import com.mfigueroa.msvc_usuarios.clients.CursoClient;
import com.mfigueroa.msvc_usuarios.models.entity.Usuario;
import com.mfigueroa.msvc_usuarios.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private CursoClient cursoClient;

    @Override
    public List<Usuario> findAll() {
        return usuariosRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuariosRepository.findById(id);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuariosRepository.findByEmail(email);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuariosRepository.save(usuario);
    }

    @Override
    public void delete(long id) {
        Usuario usuario = usuariosRepository.findById(id).orElseThrow(
                RuntimeException::new
        );
        cursoClient.desenrolarDeCursos(id);
        usuariosRepository.delete(usuario);
    }

    @Override
    public List<Usuario> findAll(List<Long> ids) {
        return usuariosRepository.findAllById(ids);
    }
}
