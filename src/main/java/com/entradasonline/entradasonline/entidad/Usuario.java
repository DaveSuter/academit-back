package com.entradasonline.entradasonline.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private int idUsuario;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @OneToOne
    @JoinColumn(name = "iddatos")
    private DatosUsuario datosUsuario;

}
