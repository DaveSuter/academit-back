package com.entradasonline.entradasonline.servicio;

import com.entradasonline.entradasonline.entidad.DatosUsuario;
import com.entradasonline.entradasonline.entidad.Usuario;
import com.entradasonline.entradasonline.exception.BadRequestException;
import com.entradasonline.entradasonline.exception.ErrorProcessException;
import com.entradasonline.entradasonline.exception.NotFoundException;
import com.entradasonline.entradasonline.negocio.dto.UsuarioDTO;
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
    public List<Usuario> getAll() throws ErrorProcessException {
        try {
            return repository.findAll();
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ex.getMessage());
        }
    }

    @Override
    public Optional<Usuario> findById(int id) throws ErrorProcessException {
        try {
            return repository.findById(id);
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ex.getMessage());
        }
    }

    @Override
    public Optional<Usuario> findByEmail(String email) throws ErrorProcessException {
        try {
            return repository.findByEmail(email);
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ex.getMessage());
        }
    }

    @Override
    public Usuario findByEmailAndPass(String email, String password) throws ErrorProcessException {
        try {
            return repository.buscarPorEmailYPassword(email, password);
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ex.getMessage());
        }
    }

    @Override
    public boolean showExist(String email) throws ErrorProcessException {
        try {
            return repository.findByEmail(email).isPresent();
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ex.getMessage());
        }
    }

    @Override
    public Usuario save(UsuarioDTO usuarioDto) throws ErrorProcessException {
        Usuario usuario = repository.buscarPorEmailYPassword(usuarioDto.getEmail(), usuarioDto.getPassword());
        if (usuario != null){
            throw new BadRequestException("this user already exist");
        }
        try {
            DatosUsuario datosU = datosRepository.save(usuario.getDatosUsuario());
            usuario.setDatosUsuario(datosU);
            return repository.save(usuario);
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ex.getMessage());
        }
    }

    @Override
    public Usuario update(String email, UsuarioDTO usuarioDto) throws ErrorProcessException {
        Usuario usuarioUpdate = repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("this show cannot found in the database"));
        try {
            usuarioUpdate.setEmail(usuarioDto.getEmail());//
            usuarioUpdate.setPassword(usuarioDto.getPassword());
            return repository.save(usuarioUpdate);
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ex.getMessage());
        }
    }

    @Override
    public void delete(String email) throws ErrorProcessException {
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("this user doesn't exist"));
        try {
            repository.deleteById(usuario.getIdUsuario());
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ex.getMessage());
        }
    }
}
