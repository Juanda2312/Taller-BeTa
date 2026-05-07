package co.edu.uniquindio.tallerbeta.model.entity;

import co.edu.uniquindio.tallerbeta.model.Enum.Especializacion;
import co.edu.uniquindio.tallerbeta.model.Enum.Estado;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa a un mecánico en el sistema del taller.
 * Un mecánico es un tipo de persona con una especialización y puede tener una orden asignada para mantenimiento.
 */
@Getter
@Setter
@NoArgsConstructor
public class Mecanico extends Persona {

    Especializacion especializacion;
    @JsonBackReference
    Orden ordenAsignada;

    /**
     * Constructor que inicializa el mecánico con nombre, cédula y especialización.
     * La orden asignada se inicializa como null.
     *
     * @param nombre El nombre del mecánico.
     * @param cedula La cédula del mecánico.
     * @param especializacion La especialización del mecánico.
     */
    public Mecanico(String nombre, String cedula, Especializacion especializacion) {
        super(nombre, cedula);
        this.especializacion = especializacion;
        this.ordenAsignada = null;
    }

    /**
     * Método que calcula el presupuesto para la orden asignada basado en la especialización del mecánico.
     * Si no hay orden asignada, retorna 0.
     *
     * @return El presupuesto calculado para la orden.
     */
    public double hacerPresupuesto() {
        if (ordenAsignada == null) return 0;
        // presupuesto base según especialización
        return switch (especializacion) {
            case AUTOMOTRIZ -> 150000;
            case ELECTRICO  -> 200000;
            case DIESEL     -> 180000;
        };
    }

    /**
     * Método que realiza el mantenimiento de la orden asignada.
     * Cambia el estado de la orden a FINALIZADO y libera la asignación.
     * Si no hay orden asignada, lanza una excepción.
     *
     * @return La orden finalizada.
     * @throws RuntimeException Si no hay una orden asignada.
     */
    public Orden realizarMantenimiento() {
        if (ordenAsignada == null)
            throw new RuntimeException("No tiene una orden asignada.");
        ordenAsignada.setEstado(Estado.FINALIZADO);
        Orden ordenFinalizada = ordenAsignada;
        this.ordenAsignada = null;
        return ordenFinalizada;
    }
}