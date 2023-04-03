package com.entradasonline.entradasonline.repositorio;

import com.entradasonline.entradasonline.entidad.DatosUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatosUsuarioJpaRepository extends JpaRepository<DatosUsuario, Integer> {

}
