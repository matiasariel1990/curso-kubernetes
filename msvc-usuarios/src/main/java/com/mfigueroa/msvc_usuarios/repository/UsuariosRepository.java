package com.mfigueroa.msvc_usuarios.repository;

import com.mfigueroa.msvc_usuarios.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Long> {
        Optional<Usuario> findByEmail(String email);
}
