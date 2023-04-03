package com.entradasonline.entradasonline.negocio.dto;


import com.entradasonline.entradasonline.entidad.Genero;
import com.entradasonline.entradasonline.entidad.Recinto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspectaculoDTO {
    @NotNull
    @NotBlank
    @NotEmpty
    private int id;
    @NotNull
    @NotBlank
    @NotEmpty
    private String nombre;
    private String imgcard;
    private String imgbaner;

    private LocalDate fecha;

    private String descripcion;
    private Genero genero;
    private Recinto recinto;
}
