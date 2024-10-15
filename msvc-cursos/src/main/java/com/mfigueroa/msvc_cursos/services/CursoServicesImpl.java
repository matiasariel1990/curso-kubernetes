package com.mfigueroa.msvc_cursos.services;

import com.mfigueroa.msvc_cursos.clients.UsuariosClient;
import com.mfigueroa.msvc_cursos.models.entity.Curso;
import com.mfigueroa.msvc_cursos.exception.ResourseNotFoundException;
import com.mfigueroa.msvc_cursos.models.entity.CursoUsuario;
import com.mfigueroa.msvc_cursos.models.entity.Usuario;
import com.mfigueroa.msvc_cursos.repositories.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CursoServicesImpl implements CursoService{

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    UsuariosClient usuariosClient;

    @Override
    public List<Curso> getAll() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso getById(long id) {
        return cursoRepository.findById(id)
                .orElseThrow(
                        ResourseNotFoundException::new
                );
    }

    @Override
    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void delete(long id){
        Curso curso = this.getById(id);
        cursoRepository.delete(curso);
    }

    @Override
    public Optional<Usuario> asignarUsuario(Usuario usuario, long idCurso) {
        Curso cursoDb = this.getById(idCurso);
        CursoUsuario cursoUsuario = new CursoUsuario();
        cursoUsuario.setUsuarioId(usuario.getId());
        cursoDb.getCursoUsuarios().add(cursoUsuario);
        cursoRepository.save(cursoDb);
        return Optional.empty();
    }

    @Transactional
    @Override
    public Curso asignarUsuario(long idCurso, long idUsuario) {
        Usuario usuario = usuariosClient.getUsuario(idUsuario);
        if(usuario == null){
            throw new ResourseNotFoundException();
        }
        Curso cursoDb = this.getById(idCurso);
        CursoUsuario cursoUsuario = new CursoUsuario();
        cursoUsuario.setUsuarioId(usuario.getId());
        cursoDb.getCursoUsuarios().add(cursoUsuario);
        cursoRepository.save(cursoDb);
        return cursoDb;
    }

    @Transactional
    @Override
    public Curso desAsignarUsuario(long idCurso, long idUsuario) {
        Curso cursoDb = this.getById(idCurso);
        CursoUsuario cursoUsuario = new CursoUsuario();
        cursoUsuario.setUsuarioId(idUsuario);
        cursoDb.getCursoUsuarios().remove(cursoUsuario);
        cursoRepository.save(cursoDb);
        return cursoDb;
    }

    @Override
    public List<Usuario> getDetalleUsuariosPorCurso(long idCurso) {
        Curso cursoDb = this.getById(idCurso);
        List<Long> usuarioIds = new ArrayList<>();
        for(CursoUsuario cursoUsuario : cursoDb.getCursoUsuarios()){
            usuarioIds.add(cursoUsuario.getUsuarioId());
        }
        List<Usuario> usuarioList = usuariosClient.getAllById(usuarioIds);
        return usuarioList;
    }

    @Override
    public void desenrolarUsuarioATodosLosCursos(long id) {
        cursoRepository.eliminarCursoUsuarioPorIdUsuario(id);
    }


}
