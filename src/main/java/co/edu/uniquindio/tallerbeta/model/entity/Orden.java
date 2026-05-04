package co.edu.uniquindio.tallerbeta.model.entity;

import co.edu.uniquindio.tallerbeta.model.Enum.Estado;

import java.util.UUID;

public class Orden {
    UUID id;
    String instrucciones;
    Mecanico mecanico;
    Cliente cliente;
    Estado estado;

    public Orden(UUID id, String instrucciones, Mecanico mecanico, Cliente cliente, Estado estado){
        this.id = id;
        this.instrucciones = instrucciones;
        this.mecanico = mecanico;
        this.cliente = cliente;
        this.estado = estado;
    }
}
