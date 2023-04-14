package com.entradasonline.entradasonline.controlador;

import com.entradasonline.entradasonline.entidad.Usuario;
import com.entradasonline.entradasonline.negocio.dto.UsuarioDTO;
import com.entradasonline.entradasonline.negocio.dto.mapper.UsuarioMapper;
import com.entradasonline.entradasonline.servicio.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;


import javax.validation.Valid;
import java.util.Optional;


@CrossOrigin("http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController {
    private final UsuarioService service;

    @PostMapping("/login")
    public ResponseEntity<?> traerUsuario(@RequestBody UsuarioDTO usuarioDTO){
        log.info("Email ingresado: " + usuarioDTO.getEmail());
        log.info("Pass ingresado: " + usuarioDTO.getPassword());

        Optional<Usuario> oUsuario = this.service.findByEmailAndPass(usuarioDTO.getEmail(), usuarioDTO.getPassword());
        if (oUsuario.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(oUsuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado.");
        }

    }

    @PostMapping("/")
    //public ResponseEntity<?> altaUsuario(@Valid @RequestBody UsuarioDTO usuarioDto, BindingResult result) {
    public ResponseEntity<?> altaUsuario(@Valid @RequestBody UsuarioDTO usuarioDto) {
        log.info("PersonajeDTO " + usuarioDto.toString());

        Usuario uCreado;
        try {
            Usuario usuario = UsuarioMapper.dtoToEntity(usuarioDto);
            uCreado = service.save(usuario);
        } catch (RuntimeException e) {
            //throw e;
            log.warn("Exception en alta de usuario: " + e.getMessage());
            log.warn("exception: " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("Usuario creado: \n" + uCreado.toString());
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> modificarUsuario(@PathVariable String email, @RequestBody UsuarioDTO usuarioDTO){
        Usuario updateUsuario;
        try {
            Usuario usuario = UsuarioMapper.dtoToEntity(usuarioDTO);
            updateUsuario = service.update(email, usuario);
        }catch (RuntimeException e){
            log.warn("Exception al modificar usuario: " + e.getMessage());
            log.warn("exception: " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("El usuario se modifico con éxito.\n" + updateUsuario.toString());
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> borrarUsuario(@PathVariable String email){

        try{
            this.service.delete(email);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Borrado exitoso.");
        //return new ResponseEntity<>("Borrado exitoso.", HttpStatus.OK);
    }

}
