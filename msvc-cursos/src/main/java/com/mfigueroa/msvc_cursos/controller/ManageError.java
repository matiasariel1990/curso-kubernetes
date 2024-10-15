package com.mfigueroa.msvc_cursos.controller;

import com.mfigueroa.msvc_cursos.exception.ResourseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ManageError {

    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<?> handleValidationErrors(ResourseNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el recurso");
    }
}
