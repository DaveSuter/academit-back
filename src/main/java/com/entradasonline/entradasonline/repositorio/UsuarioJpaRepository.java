package com.entradasonline.entradasonline.repositorio;

import com.entradasonline.entradasonline.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioJpaRepository extends JpaRepository<Usuario, Integer> {
    @Query("from Usuario u where u.email = ?1")
    Optional<Usuario> buscarPorEmail(String email);
    //cambiar por findById

    Optional<Usuario> findById(int id);


    @Query("from Usuario u where u.email = ?1 and u.password = ?2")
    Optional<Usuario> buscarPorEmailYPassword(String email, String password);
}