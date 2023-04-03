package com.entradasonline.entradasonline.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ubicacion")
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idubicacion")
    private int idubicacion;
    @Column(name = "sector")
    private String sector;
    @Column(name = "cantidadinicial")
    private int cantidadInicial;
    @Column(name = "cantidadvendida")
    private int cantidadVendida;
    @Column(name = "precio")
    private double precio;
}
