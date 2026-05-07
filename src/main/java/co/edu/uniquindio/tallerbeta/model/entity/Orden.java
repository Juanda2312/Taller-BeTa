package co.edu.uniquindio.tallerbeta.model.entity;

import co.edu.uniquindio.tallerbeta.model.Enum.Estado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.UUID;

/**
 * Clase que representa una orden de mantenimiento en el sistema.
 * Una orden tiene un ID único, instrucciones, un mecánico asignado opcional, un cliente y un estado.
 */
@Setter
@Getter
@NoArgsConstructor
public class Orden {
    UUID id;
    String instrucciones;
    @JsonManagedReference
    Mecanico mecanico;
    Cliente cliente;
    Estado estado;

    /**
     * Constructor que inicializa la orden con instrucciones y cliente.
     * Genera un ID único, asigna estado SINASIGNAR y mecánico null.
     *
     * @param instrucciones Las instrucciones de la orden.
     * @param cliente El cliente que solicita la orden.
     */
    public Orden(String instrucciones, Cliente cliente){
        this.id = UUID.randomUUID();
        this.instrucciones = instrucciones;
        this.mecanico = null;
        this.cliente = cliente;
        this.estado = Estado.SINASIGNAR;
    }
}
