package com.mfigueroa.msvc_usuarios.controllers;

import com.mfigueroa.msvc_usuarios.models.entity.Usuario;
import com.mfigueroa.msvc_usuarios.services.UsuarioService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id){
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable   long id,@Valid @RequestBody Usuario usuario, BindingResult result){


        if(result.hasErrors()){
            return validRequest(result);
        }
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuarioEnDb = usuarioOptional.get();
            if(!usuarioEnDb.getEmail().equals(usuario.getEmail()) && usuarioService.findByEmail(usuario.getEmail()).isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        Collections.singletonMap("mensaje", "Ya existe un usuario con ese correo electronico."));
            }
            usuarioEnDb.setEmail(usuario.getEmail());
            usuarioEnDb.setNombre(usuario.getNombre());
            usuarioEnDb.setPassword(usuario.getPassword());
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result){
        if(usuarioService.findByEmail(usuario.getEmail()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Collections.singletonMap("mensaje", "Ya existe un usuario con ese correo electronico."));
        }
        if(result.hasErrors()){
            return validRequest(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    private static ResponseEntity<Map<String, String>> validRequest(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err ->
        {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        try {
            usuarioService.delete(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/lista/")
    public ResponseEntity<?> getAllById(List<Long> usuariosList){
        return ResponseEntity.ok().body(usuarioService.findAll(usuariosList));
    }


}
