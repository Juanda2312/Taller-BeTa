package co.edu.uniquindio.tallerbeta.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
public class Administrador extends Usuario{

    LinkedList<Mecanico> mecanicosDisponibles;

    public Administrador(){
        super();
        mecanicosDisponibles = new LinkedList<>();
    }

    public Administrador(String nombre, String cedula, String correo, String contrasenia) {
        super(nombre, cedula, correo, contrasenia);
        mecanicosDisponibles = new LinkedList<>();
    }

    public Mecanico asignarMecanico(){
        return mecanicosDisponibles.removeFirst();
    }


}
