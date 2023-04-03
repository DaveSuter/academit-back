package com.entradasonline.entradasonline.repositorio;

import com.entradasonline.entradasonline.datos.DatosDummy;
import com.entradasonline.entradasonline.entidad.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UsuarioJpaRepositoryTest {
    @Autowired
    private UsuarioJpaRepository repository;
    @Autowired
    private DatosUsuarioJpaRepository datosRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void buscarPorEmail() {
        datosRepository.save(DatosDummy.getUser().getDatosUsuario());
        repository.save(DatosDummy.getUser());
        Optional<Usuario> optionalUsuario = repository.buscarPorEmail("test@test.com");
        assertThat(optionalUsuario.isPresent()).isTrue();
        assertThat(optionalUsuario.get().getEmail()).isEqualTo("test@test.com");
    }

    @Test
    void buscarPorEmailYPassword() {
        datosRepository.save(DatosDummy.getUser().getDatosUsuario());
        repository.save(DatosDummy.getUser());
        Optional<Usuario> optionalUsuario = repository.buscarPorEmailYPassword("test@test.com", "1234");
        assertThat(optionalUsuario.isPresent()).isTrue();
        assertThat(optionalUsuario.get().getEmail()).isEqualTo("test@test.com");
        assertThat(optionalUsuario.get().getPassword()).isEqualTo("1234");
    }
}