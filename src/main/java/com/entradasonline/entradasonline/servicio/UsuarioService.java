package com.entradasonline.entradasonline.servicio;

import com.entradasonline.entradasonline.entidad.Usuario;
import com.entradasonline.entradasonline.exception.ErrorProcessException;
import com.entradasonline.entradasonline.negocio.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> getAll() throws ErrorProcessException;
    Optional<Usuario> findById(int id) throws ErrorProcessException;
    Optional<Usuario> findByEmail(String email) throws ErrorProcessException;
    Usuario findByEmailAndPass(String email, String password) throws ErrorProcessException;
    boolean showExist(String email) throws ErrorProcessException;
    Usuario save(UsuarioDTO usuarioDto) throws ErrorProcessException;
    Usuario update(String email, UsuarioDTO usuarioDto) throws ErrorProcessException;
    void delete(String email) throws ErrorProcessException;
}
