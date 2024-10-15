package com.mfigueroa.msvc_cursos.services;

import com.mfigueroa.msvc_cursos.models.entity.Curso;
import com.mfigueroa.msvc_cursos.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<Curso> getAll();

    Curso getById(long id);

    Curso save(Curso curso);

    void delete(long id);

    Optional<Usuario> asignarUsuario(Usuario usuario, long idCurso);

    Curso asignarUsuario(long idCurso, long idUsuario);

    Curso desAsignarUsuario(long idCurso, long idUsuario);

    List<Usuario> getDetalleUsuariosPorCurso(long idCurso);

    void desenrolarUsuarioATodosLosCursos(long id);
}
