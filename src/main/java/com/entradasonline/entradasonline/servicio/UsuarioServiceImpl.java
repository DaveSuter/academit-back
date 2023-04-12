package com.entradasonline.entradasonline.servicio;

import com.entradasonline.entradasonline.entidad.DatosUsuario;
import com.entradasonline.entradasonline.entidad.Usuario;
import com.entradasonline.entradasonline.repositorio.DatosUsuarioJpaRepository;
import com.entradasonline.entradasonline.repositorio.UsuarioJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private  final UsuarioJpaRepository repository;
    private final DatosUsuarioJpaRepository datosRepository;

    @Override
    public List<Usuario> getAll() {
        return (List<Usuario>) this.repository.findAll();
    }

    @Override
    public Optional<Usuario> findById(int id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    @Override
    public Optional<Usuario> findByEmailAndPass(String email, String password) {
        return this.repository.buscarPorEmailYPassword(email, password);
    }

    @Override
    public boolean showExist(String email) {
        return this.repository.findByEmail(email).isPresent();
    }

    @Override
    public Usuario save(Usuario usuario) {
        if (this.showExist(usuario.getEmail())){
            throw new RuntimeException(
                String.format("El usuario con el email s% ya existe", usuario.getEmail())
            );
        }
        DatosUsuario datosU = datosRepository.save(usuario.getDatosUsuario());
        usuario.setDatosUsuario(datosU);
        return this.repository.save(usuario);
    }

    @Override
    public Usuario update(String email, Usuario usuario) {
        Usuario usuarioUpdate;
        Optional<Usuario> oUsuario= this.repository.findByEmail(email);

        if (oUsuario.isPresent()){
            usuarioUpdate = oUsuario.get();
        } else {
            throw new RuntimeException("El usuario con email: " + email + " no existe.");
        }

        usuarioUpdate.setEmail(usuario.getEmail());
        usuarioUpdate.setPassword(usuario.getPassword());

        return this.repository.save(usuarioUpdate);
    }

    @Override
    public void delete(String email) {
        Usuario usuario;
        Optional<Usuario> optionalUsuario = this.repository.findByEmail(email);
        if (optionalUsuario.isEmpty()){
            throw new RuntimeException("El usuario con email " + email + " no existe.");
        } else {
            usuario = optionalUsuario.get();
        }
        this.repository.deleteById(usuario.getIdUsuario());
    }
}
