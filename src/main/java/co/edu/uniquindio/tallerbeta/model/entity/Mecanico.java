package co.edu.uniquindio.tallerbeta.model.entity;

import co.edu.uniquindio.tallerbeta.model.Enum.Especializacion;
import co.edu.uniquindio.tallerbeta.model.Enum.Estado;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Mecanico extends Persona {

    Especializacion especializacion;
    @JsonBackReference
    Orden ordenAsignada;

    public Mecanico(String nombre, String cedula, Especializacion especializacion) {
        super(nombre, cedula);
        this.especializacion = especializacion;
        this.ordenAsignada = null;
    }

    public double hacerPresupuesto() {
        if (ordenAsignada == null) return 0;
        // presupuesto base según especialización
        return switch (especializacion) {
            case AUTOMOTRIZ -> 150000;
            case ELECTRICO  -> 200000;
            case DIESEL     -> 180000;
        };
    }

    public Orden realizarMantenimiento() {
        if (ordenAsignada == null)
            throw new RuntimeException("No tiene una orden asignada.");
        ordenAsignada.setEstado(Estado.FINALIZADO);
        Orden ordenFinalizada = ordenAsignada;
        this.ordenAsignada = null;
        return ordenFinalizada;
    }
}