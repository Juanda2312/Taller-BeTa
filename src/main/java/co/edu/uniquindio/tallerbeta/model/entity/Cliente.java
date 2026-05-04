package co.edu.uniquindio.tallerbeta.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
public class Cliente extends Usuario{

    LinkedList<Vehiculo> ListaVehiculos;
    LinkedList<Orden> ListaOrdenes;

    public Cliente(String nombre, String cedula, String correo, String contrasenia) {
        super(nombre, cedula, correo, contrasenia);
        this.ListaVehiculos = new LinkedList<>();
        this.ListaOrdenes = new LinkedList<>();
    }

}
