package co.edu.uniquindio.tallerbeta.model.entity;

import co.edu.uniquindio.tallerbeta.model.Enum.Estado;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class Orden {
    UUID id;
    String instrucciones;
    Mecanico mecanico;
    Cliente cliente;
    Estado estado;

    public Orden(String instrucciones, Cliente cliente){
        this.id = UUID.randomUUID();
        this.instrucciones = instrucciones;
        this.mecanico = null;
        this.cliente = cliente;
        this.estado = Estado.SINASIGNAR;
    }
}
