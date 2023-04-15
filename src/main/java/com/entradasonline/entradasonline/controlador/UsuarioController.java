package com.entradasonline.entradasonline.controlador;

import com.entradasonline.entradasonline.exception.ErrorProcessException;
import com.entradasonline.entradasonline.negocio.dto.UsuarioDTO;
import com.entradasonline.entradasonline.servicio.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;


import javax.validation.Valid;

@CrossOrigin("http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController {
    private final UsuarioService service;

    @PostMapping("/login")
    public ResponseEntity<?> traerUsuario(@RequestBody UsuarioDTO usuarioDTO) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findByEmailAndPass(usuarioDTO.getEmail(), usuarioDTO.getPassword()));
    }

    @PostMapping("/")
    public ResponseEntity<?> altaUsuario(@Valid @RequestBody UsuarioDTO usuarioDto) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(service.save(usuarioDto));
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> modificarUsuario(@PathVariable String email, @RequestBody UsuarioDTO usuarioDTO)
            throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(email, usuarioDTO));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> borrarUsuario(@PathVariable String email) throws ErrorProcessException {
        service.delete(email);
        return ResponseEntity.status(HttpStatus.OK).body("Borrado exitoso.");
    }
}
