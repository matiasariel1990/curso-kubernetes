package com.mfigueroa.msvc_cursos.clients;

import com.mfigueroa.msvc_cursos.models.entity.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name ="${service.usuarios.name}", url="${service.usuarios.url}")
public interface UsuariosClient {

    @GetMapping("/{id}")
    Usuario getUsuario(@PathVariable Long id);

    @PostMapping("/")
    Usuario createUsuario(@RequestBody Usuario usuario);

    @GetMapping
    List<Usuario> getAllById(@RequestParam List<Long> ids);

}
