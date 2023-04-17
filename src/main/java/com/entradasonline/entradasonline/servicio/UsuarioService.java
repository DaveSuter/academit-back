package com.entradasonline.entradasonline.servicio;

import com.entradasonline.entradasonline.entidad.Usuario;
import com.entradasonline.entradasonline.exception.ErrorProcessException;
import com.entradasonline.entradasonline.negocio.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UsuarioDTO> getAll() throws ErrorProcessException;
    UsuarioDTO findById(int id) throws ErrorProcessException;
    UsuarioDTO findByEmail(String email) throws ErrorProcessException;
    UsuarioDTO findByEmailAndPass(String email, String password) throws ErrorProcessException;
    boolean showExist(String email) throws ErrorProcessException;
    UsuarioDTO save(UsuarioDTO usuarioDto) throws ErrorProcessException;
    UsuarioDTO update(String email, UsuarioDTO usuarioDto) throws ErrorProcessException;
    void delete(String email) throws ErrorProcessException;
}
