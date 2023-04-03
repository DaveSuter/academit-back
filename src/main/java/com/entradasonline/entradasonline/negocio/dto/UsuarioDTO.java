package com.entradasonline.entradasonline.negocio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    @NotNull
    @NotBlank
    @NotEmpty
    private String email;
    @NotNull
    @NotBlank
    @NotEmpty
    private String password;
}
