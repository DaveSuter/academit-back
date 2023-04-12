package com.entradasonline.entradasonline.servicio;


import com.entradasonline.entradasonline.entidad.Espectaculo;
import com.entradasonline.entradasonline.exception.ErrorProcessException;
import com.entradasonline.entradasonline.negocio.dto.EspectaculoDTO;


import java.util.List;
import java.util.Optional;

public interface EspectaculoService {
    List<EspectaculoDTO> getAll() throws ErrorProcessException;
    List<Espectaculo> getBanners();
    Optional<Espectaculo> findById(int id);
    Optional<Espectaculo>findByNombre(String nombre);
    boolean showExist(String nombre);
    Espectaculo save(Espectaculo espectaculo);
    Espectaculo update(int id, Espectaculo espectaculo);
    void delete(int id);

}
