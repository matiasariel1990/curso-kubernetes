package com.mfigueroa.msvc_cursos.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "curso_usuarios" )
@Getter
@Setter
public class CursoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "usuario_id")
    private Long usuarioId;

    @Override
    public boolean equals(Object object){
        if(!(object instanceof CursoUsuario)){
            return  false;
        }
        CursoUsuario o = (CursoUsuario) object;
        return this.usuarioId != null && this.usuarioId.equals(o.getUsuarioId());
    };
}
