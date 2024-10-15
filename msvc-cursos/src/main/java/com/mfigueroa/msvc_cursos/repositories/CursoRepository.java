package com.mfigueroa.msvc_cursos.repositories;


import com.mfigueroa.msvc_cursos.models.entity.Curso;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("delete from CursoUsuario cu where cu.usuarioId= :id")
    @Modifying
    @Transactional
    void eliminarCursoUsuarioPorIdUsuario(@Param("id") Long id);
}
