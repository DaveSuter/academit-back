package com.entradasonline.entradasonline.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "espectaculos")
public class Espectaculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idshow")
    private int idshow;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "imgcard")
    private String imgCard;
    @Column(name = "imgbaner")
    private String imgBaner;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "descripcion")
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "idgenero")
    private Genero genero;
    @ManyToOne
    @JoinColumn(name = "idrecinto")
    private Recinto recinto;

}
