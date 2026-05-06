package co.edu.uniquindio.tallerbeta.model.entity;

import co.edu.uniquindio.tallerbeta.model.Enum.Estado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.UUID;

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

    public Orden(String instrucciones, Cliente cliente){
        this.id = UUID.randomUUID();
        this.instrucciones = instrucciones;
        this.mecanico = null;
        this.cliente = cliente;
        this.estado = Estado.SINASIGNAR;
    }
}
