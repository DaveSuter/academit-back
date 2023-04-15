package com.entradasonline.entradasonline.servicio;

import com.entradasonline.entradasonline.datos.DatosDummy;
import com.entradasonline.entradasonline.entidad.DatosUsuario;
import com.entradasonline.entradasonline.entidad.Usuario;
import com.entradasonline.entradasonline.repositorio.DatosUsuarioJpaRepository;
import com.entradasonline.entradasonline.repositorio.UsuarioJpaRepository;
import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;


@SpringBootTest
class UsuarioServiceImplTest {
    @MockBean
    private UsuarioJpaRepository uRepository;
    @MockBean
    private DatosUsuarioJpaRepository datosRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Test
    void getAll() {
    }
/*
    @Test
    void findById() {
        Integer id = 1;
        when(uRepository.findById(id)).thenReturn(Optional.of(DatosDummy.getUser()));
        Optional<Usuario> oUsuario = usuarioService.findById(id);
        assertThat(oUsuario.get().getIdUsuario()).isEqualTo(1);
    }

    @Test
    void findByEmail() {
        Usuario usuario = DatosDummy.getUser();

        given(uRepository.buscarPorEmail(usuario.getEmail())).willReturn(Optional.of(usuario));
        Optional<Usuario> optionalUsuario = usuarioService.findByEmail(usuario.getEmail());

        assertThat(optionalUsuario.get().getEmail()).isEqualTo(usuario.getEmail());
    }

    @Test
    void findByEmailAndPass() {
        Usuario usuario = DatosDummy.getUser();

        given(uRepository.buscarPorEmailYPassword(usuario.getEmail(), usuario.getPassword())).willReturn(Optional.of(usuario));
        Optional<Usuario> oUsuario = usuarioService.findByEmailAndPass(usuario.getEmail(), usuario.getPassword());

        assertThat(oUsuario.get().getEmail()).isEqualTo(usuario.getEmail());
        assertThat(oUsuario.get().getPassword()).isEqualTo(usuario.getPassword());
    }


    @Test
    void showExist() {
        String email = "test@test.com";
        given(uRepository.buscarPorEmail(email)).willReturn(Optional.of(DatosDummy.getUser()));

        Boolean existUserByEmail = usuarioService.showExist(email);
        assertThat(existUserByEmail).isTrue();
    }

    @Test
    void save() {
        Usuario usuario = DatosDummy.getUser();
        usuarioService.save(usuario);
        ArgumentCaptor<Usuario> usuarioArgumentCaptor = ArgumentCaptor.forClass(Usuario.class);
        verify(uRepository).save(usuarioArgumentCaptor.capture());
        Usuario usuarioCaptor = usuarioArgumentCaptor.getValue();
        assertThat(usuarioCaptor).isEqualTo(usuario);
        verify(uRepository).save(any());
    }

    @Test
    void saveEx(){
        Usuario usuario = DatosDummy.getUser();
        given(uRepository.buscarPorEmail(usuario.getEmail())).willReturn(Optional.of(usuario));
        assertThatThrownBy(() -> usuarioService.save(usuario)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void update() {
        String email = "test@test.com";
        DatosUsuario datosUsuario = new DatosUsuario(1, "David", "Suter", 33222111);
        Usuario usuario = new Usuario(1, "test@test.com", "4321", datosUsuario);

        given(uRepository.buscarPorEmail(email)).willReturn(Optional.of(DatosDummy.getUser()));
        given(datosRepository.findById(1)).willReturn(Optional.of(DatosDummy.getUser().getDatosUsuario()));
        given(usuarioService.update(email, usuario)).willReturn(usuario);
        Usuario usuarioActualizado = usuarioService.update(email, usuario);

        ArgumentCaptor<Usuario> usuarioArgumentCaptor = ArgumentCaptor.forClass(Usuario.class);
        verify(uRepository).save(usuarioArgumentCaptor.capture());
        assertThat(usuarioActualizado.getPassword()).isEqualTo("4321");
        assertThat(usuarioActualizado.getDatosUsuario().getNombre()).isEqualTo("David");
    }

    @Test
    void updateEx(){
        String email = "test@test.com";
        DatosUsuario datosUsuario = new DatosUsuario(1, "David", "Suter", 33222111);
        Usuario usuario = new Usuario(1, "test@test.com", "4321", datosUsuario);

        given(uRepository.buscarPorEmail(email)).willReturn(Optional.empty());
        assertThatThrownBy(() ->usuarioService.update(email, usuario)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void delete() {
        Integer id = 1;
        String email = "test@test.com";

        given(uRepository.buscarPorEmail(email)).willReturn(Optional.of(DatosDummy.getUser()));
        willDoNothing().given(uRepository).deleteById(id);
        willDoNothing().given(datosRepository).deleteById(DatosDummy.getUser().getDatosUsuario().getIdDatosUsuario());
        usuarioService.delete(email);

        verify(uRepository, times(1)).deleteById(any());
    }

    @Test
    void deleteEx(){
        Integer id = 1;
        String email = "test@test.com";

        given(uRepository.buscarPorEmail(email)).willReturn(Optional.empty());
        assertThatThrownBy(() -> usuarioService.delete(email)).isInstanceOf(RuntimeException.class);
    }*/
}