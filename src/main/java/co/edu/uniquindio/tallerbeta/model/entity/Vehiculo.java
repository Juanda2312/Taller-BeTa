package co.edu.uniquindio.tallerbeta.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

/**
 * Clase que representa un vehículo en el sistema.
 * Un vehículo tiene matrícula, modelo y una lista de daños reportados.
 */
@Getter
@Setter
public class Vehiculo {
    String matricula,modelo;
    LinkedList<String> daños = new LinkedList<>();

    /**
     * Constructor por defecto que inicializa la lista de daños.
     */
    public Vehiculo(){
        daños = new LinkedList<>();
    }

    /**
     * Constructor que inicializa el vehículo con matrícula y modelo.
     *
     * @param matricula La matrícula del vehículo.
     * @param modelo El modelo del vehículo.
     */
    public Vehiculo(String matricula, String modelo) {
        this.matricula = matricula;
        this.modelo = modelo;
    }

}
