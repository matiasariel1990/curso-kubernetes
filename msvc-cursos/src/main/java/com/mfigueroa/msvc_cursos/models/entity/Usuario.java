package com.mfigueroa.msvc_cursos.models.entity;

import lombok.Data;

@Data
public class Usuario {
   private long id;

    private String nombre;

    private String email;

    private String password;
}
