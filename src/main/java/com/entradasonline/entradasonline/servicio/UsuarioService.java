package com.entradasonline.entradasonline.servicio;

import com.entradasonline.entradasonline.entidad.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> getAll();
    Optional<Usuario> findById(int id);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByEmailAndPass(String email, String password);
    boolean showExist(String email);
    Usuario save(Usuario usuario);
    Usuario update(String email, Usuario usuario);
    void delete(String email);
}
