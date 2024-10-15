package com.mfigueroa.msvc_usuarios.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name ="usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotEmpty
    private String nombre;

    @Column(unique = true)
    @NotEmpty
    @Email
    private String email;

    @Column(unique = false)
    @NotBlank
    private String password;

}
