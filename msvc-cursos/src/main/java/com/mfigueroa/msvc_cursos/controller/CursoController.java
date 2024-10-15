package com.mfigueroa.msvc_cursos.controller;

import com.mfigueroa.msvc_cursos.models.entity.Curso;
import com.mfigueroa.msvc_cursos.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    CursoService cursoService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(cursoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id){
        return ResponseEntity.ok(cursoService.getById(id));
    }


    @GetMapping("/{id}/usuarios")
    public ResponseEntity<?> getenroladosById(@PathVariable long id){

        return ResponseEntity.ok(cursoService.getDetalleUsuariosPorCurso(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> create(@PathVariable long id, @RequestBody Curso curso){
        Curso cursoDb = cursoService.getById(id);
        cursoDb.setNombre(curso.getNombre());
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.save(cursoDb));
    }


    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Curso curso){
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(curso));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable long id){
        cursoService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{idCurso}/enrolamiento/{idUsuario}")
    public ResponseEntity<?> enrolarUsuario(@PathVariable long idCurso, @PathVariable long idUsuario){
        Curso curso = cursoService.asignarUsuario(idCurso, idUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    @DeleteMapping("/{idCurso}/enrolamiento/{idUsuario}")
    public ResponseEntity<?> desEnrolarUsuario(@PathVariable long idCurso, @PathVariable long idUsuario){
        Curso curso = cursoService.desAsignarUsuario(idCurso, idUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    @DeleteMapping("/enrolamiento/{idUsuario}")
    public ResponseEntity<?> desEnrolarUsuario(@PathVariable long idUsuario){
        cursoService.desenrolarUsuarioATodosLosCursos(idUsuario);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
