package co.edu.uniquindio.tallerbeta.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.LinkedList;


@Getter(onMethod_ = @JsonIgnore)
@Setter

public class Cliente extends Usuario {

    LinkedList<Vehiculo> ListaVehiculos;

    @JsonIgnore
    LinkedList<Orden> ListaOrdenes;

    public Cliente(){
        super();
        this.ListaVehiculos = new LinkedList<>();
        this.ListaOrdenes = new LinkedList<>();
    }

    public Cliente(String nombre, String cedula, String correo, String contrasenia) {
        super(nombre, cedula, correo, contrasenia);
        this.ListaVehiculos = new LinkedList<>();
        this.ListaOrdenes = new LinkedList<>();
    }

    public Orden realizarOrden(String instrucciones) {
        Orden orden = new Orden(instrucciones, this);
        this.ListaOrdenes.add(orden);
        return orden;
    }
}