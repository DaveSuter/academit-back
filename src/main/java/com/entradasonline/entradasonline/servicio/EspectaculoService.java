package com.entradasonline.entradasonline.servicio;


import com.entradasonline.entradasonline.entidad.Espectaculo;


import java.util.List;
import java.util.Optional;

public interface EspectaculoService {
    List<Espectaculo> getAll();
    List<Espectaculo> getBanners();
    Optional<Espectaculo> findById(int id);
    Optional<Espectaculo>findByNombre(String nombre);
    boolean showExist(String nombre);
    Espectaculo save(Espectaculo espectaculo);
    Espectaculo update(int id, Espectaculo espectaculo);
    void delete(int id);

}
