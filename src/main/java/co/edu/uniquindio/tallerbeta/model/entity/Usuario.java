package co.edu.uniquindio.tallerbeta.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa a un usuario en el sistema.
 * Un usuario es un tipo de persona que tiene correo electrónico y contraseña para iniciar sesión.
 */
@Setter
@Getter
@NoArgsConstructor
public class Usuario extends Persona{
    private String correo,contrasenia;

    /**
     * Constructor que inicializa el usuario con nombre, cédula, correo y contraseña.
     *
     * @param nombre El nombre del usuario.
     * @param cedula La cédula del usuario.
     * @param correo El correo electrónico del usuario.
     * @param contrasenia La contraseña del usuario.
     */
    public Usuario(String nombre, String cedula,String correo,String contrasenia) {
        super(nombre, cedula);
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    /**
     * Método que verifica las credenciales para iniciar sesión.
     *
     * @param correo El correo proporcionado.
     * @param contrasenia La contraseña proporcionada.
     * @return El usuario si las credenciales son correctas.
     * @throws RuntimeException Si las credenciales son incorrectas.
     */
    public Usuario iniciarSesion(String correo,String contrasenia){
        if (this.correo.equals(correo) && this.contrasenia.equals(contrasenia)){
            return this;
        }
        throw new RuntimeException("Credenciales Incorrectas");
    }
}
