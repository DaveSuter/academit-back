package com.entradasonline.entradasonline.negocio.dto.mapper;

import com.entradasonline.entradasonline.entidad.Usuario;
import com.entradasonline.entradasonline.negocio.dto.UsuarioDTO;

public class UsuarioMapper {
    public static Usuario dtoToEntity(UsuarioDTO dto){
        Usuario entity = new Usuario();
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    public static UsuarioDTO entityToDto(Usuario entity){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        return dto;
    }
}
