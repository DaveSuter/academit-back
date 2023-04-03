package com.entradasonline.entradasonline.negocio.dto.mapper;

import com.entradasonline.entradasonline.entidad.Espectaculo;
import com.entradasonline.entradasonline.negocio.dto.EspectaculoDTO;

public class EspectaculoMapper {
    public static Espectaculo dtoToEntity(EspectaculoDTO dto){
        Espectaculo entity = new Espectaculo();
        entity.setIdshow(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setImgCard(dto.getImgcard());
        entity.setImgBaner(dto.getImgbaner());
        return entity;
    }

    public static EspectaculoDTO entityToDto(Espectaculo entity){
        EspectaculoDTO dto = new EspectaculoDTO();
        dto.setId(entity.getIdshow());
        dto.setNombre(entity.getNombre());
        dto.setImgcard(entity.getImgCard());
        dto.setImgbaner(entity.getImgBaner());
        return dto;
    }
}
