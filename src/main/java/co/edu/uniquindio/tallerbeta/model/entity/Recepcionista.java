package co.edu.uniquindio.tallerbeta.model.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
public class Recepcionista extends Persona{

    LinkedList<Cliente> clientesatendidos;

    public Recepcionista(String nombre, String cedula) {
        super(nombre, cedula);
        clientesatendidos = new LinkedList<>();
    }

    public void AtenderCliente(Cliente cliente){

    }
}
