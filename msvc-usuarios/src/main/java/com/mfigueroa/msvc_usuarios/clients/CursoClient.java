package com.mfigueroa.msvc_usuarios.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="${curso.client.name}", url="${curso.client.url}")
public interface CursoClient {

    @DeleteMapping("/enrolamiento/{idUsuario}")
    void desenrolarDeCursos(@PathVariable long idUsuario);
}
