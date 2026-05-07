package co.edu.uniquindio.tallerbeta.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

/**
 * Clase que representa a un administrador en el sistema del taller.
 * Un administrador es un tipo de usuario que gestiona la asignación de mecánicos disponibles.
 */
@Getter
@Setter
public class Administrador extends Usuario{

    LinkedList<Mecanico> mecanicosDisponibles;

    /**
     * Constructor por defecto que inicializa la lista de mecánicos disponibles.
     */
    public Administrador(){
        super();
        mecanicosDisponibles = new LinkedList<>();
    }

    /**
     * Constructor que inicializa el administrador con nombre, cédula, correo y contraseña,
     * además de inicializar la lista de mecánicos disponibles.
     *
     * @param nombre El nombre del administrador.
     * @param cedula La cédula del administrador.
     * @param correo El correo electrónico del administrador.
     * @param contrasenia La contraseña del administrador.
     */
    public Administrador(String nombre, String cedula, String correo, String contrasenia) {
        super(nombre, cedula, correo, contrasenia);
        mecanicosDisponibles = new LinkedList<>();
    }

    /**
     * Método que asigna un mecánico disponible removiendo el primero de la lista.
     * Asume que hay mecánicos disponibles; de lo contrario, puede lanzar una excepción.
     *
     * @return El mecánico asignado.
     */
    public Mecanico asignarMecanico(){
        return mecanicosDisponibles.removeFirst();
    }


}
