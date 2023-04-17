package com.entradasonline.entradasonline.servicio;

import com.entradasonline.entradasonline.entidad.DatosUsuario;
import com.entradasonline.entradasonline.entidad.Usuario;
import com.entradasonline.entradasonline.exception.BadRequestException;
import com.entradasonline.entradasonline.exception.ErrorProcessException;
import com.entradasonline.entradasonline.exception.NotFoundException;
import com.entradasonline.entradasonline.negocio.dto.UsuarioDTO;
import com.entradasonline.entradasonline.negocio.dto.mapper.UsuarioMapper;
import com.entradasonline.entradasonline.repositorio.DatosUsuarioJpaRepository;
import com.entradasonline.entradasonline.repositorio.UsuarioJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private  final UsuarioJpaRepository repository;
    private final DatosUsuarioJpaRepository datosRepository;

    @Override
    public List<UsuarioDTO> getAll() throws ErrorProcessException {
        try {
            return repository.findAll().stream()
                    .map(UsuarioMapper::entityToDto)
                    .collect(Collectors.toList());
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ex.getMessage());
        }
    }

    @Override
    public UsuarioDTO findById(int id) throws ErrorProcessException {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found user"));
        try {
            return UsuarioMapper.entityToDto(usuario);
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ex.getMessage());
        }
    }

    @Override
    public UsuarioDTO findByEmail(String email) throws ErrorProcessException {
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Not found user"));
        try {
            return UsuarioMapper.entityToDto(usuario);
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ex.getMessage());
        }
    }

    @Override
    public UsuarioDTO findByEmailAndPass(String email, String password) throws ErrorProcessException {
        try {
            return UsuarioMapper.entityToDto(repository.buscarPorEmailYPassword(email, password));
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
    public UsuarioDTO save(UsuarioDTO usuarioDto) throws ErrorProcessException {
        Usuario usuario = repository.buscarPorEmailYPassword(usuarioDto.getEmail(), usuarioDto.getPassword());
        if (usuario != null){
            throw new BadRequestException("this user already exist");
        }
        try {
            DatosUsuario datosU = datosRepository.save(usuario.getDatosUsuario());
            usuario.setDatosUsuario(datosU);
            return UsuarioMapper.entityToDto(repository.save(usuario));
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ex.getMessage());
        }
    }

    @Override
    public UsuarioDTO update(String email, UsuarioDTO usuarioDto) throws ErrorProcessException {
        Usuario usuarioUpdate = repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("this show cannot found in the database"));
        try {
            usuarioUpdate.setEmail(usuarioDto.getEmail());//
            usuarioUpdate.setPassword(usuarioDto.getPassword());
            return UsuarioMapper.entityToDto(repository.save(usuarioUpdate));
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
