package com.entradasonline.entradasonline.servicio;


import com.entradasonline.entradasonline.entidad.Espectaculo;
import com.entradasonline.entradasonline.exception.ErrorProcessException;
import com.entradasonline.entradasonline.negocio.dto.EspectaculoDTO;


import java.util.List;
import java.util.Optional;

public interface EspectaculoService {
    List<EspectaculoDTO> getAll() throws ErrorProcessException;
    List<EspectaculoDTO> getBanners() throws ErrorProcessException;
    EspectaculoDTO findById(int id) throws ErrorProcessException;
    EspectaculoDTO findByNombre(String nombre) throws ErrorProcessException;
    boolean showExist(int id) throws ErrorProcessException;
    EspectaculoDTO save(EspectaculoDTO espectaculoDto) throws ErrorProcessException;
    Espectaculo update(int id, EspectaculoDTO espectaculoDto) throws ErrorProcessException;
    void delete(int id) throws ErrorProcessException;

}
