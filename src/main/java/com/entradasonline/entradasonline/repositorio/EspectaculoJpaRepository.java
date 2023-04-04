package com.entradasonline.entradasonline.repositorio;

import com.entradasonline.entradasonline.entidad.Espectaculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EspectaculoJpaRepository extends JpaRepository<Espectaculo, Integer> {

    @Query("from Espectaculo")
    List<Espectaculo> getAll();
    @Query("from Espectaculo s where s.nombre = ?1")
    Optional<Espectaculo> buscarPorNombre(String nombre);
    Espectaculo save(Espectaculo espectaculo);
    @Query("from Espectaculo e where e.imgBaner like '%banner%' ")
    List<Espectaculo> getBanners();

    List<Espectaculo> findTop3ByImgBanerContaining(String palabra);


}
