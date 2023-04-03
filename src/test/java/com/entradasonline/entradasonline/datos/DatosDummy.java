package com.entradasonline.entradasonline.datos;

import com.entradasonline.entradasonline.entidad.DatosUsuario;
import com.entradasonline.entradasonline.entidad.Usuario;

public class DatosDummy {
    public static Usuario getUser(){
        DatosUsuario datosUsuario = new DatosUsuario(1, "Carles", "Carless", 33444555);
        return new Usuario(1,"test@test.com", "1234", datosUsuario);


    }
}
