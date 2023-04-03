package com.entradasonline.entradasonline.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idticket")
    private int idticket;
    @Column(name = "metodopago")
    private String metodoPago;
    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "idespectaculo")
    private Espectaculo espectaculo;
    @ManyToOne
    @JoinColumn(name = "idubicacion")
    private Ubicacion ubicacion;
}
