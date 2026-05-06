package co.edu.uniquindio.tallerbeta.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Usuario extends Persona{
    private String correo,contrasenia;

    public Usuario(String nombre, String cedula,String correo,String contrasenia) {
        super(nombre, cedula);
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public Usuario iniciarSesion(String correo,String contrasenia){
        if (this.correo.equals(correo) && this.contrasenia.equals(contrasenia)){
            return this;
        }
        throw new RuntimeException("Credenciales Incorrectas");
    }


}
