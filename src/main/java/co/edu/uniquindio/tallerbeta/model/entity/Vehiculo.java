package co.edu.uniquindio.tallerbeta.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
public class Vehiculo {
    String matricula,modelo;
    LinkedList<String> daños = new LinkedList<>();

    public Vehiculo(String matricula, String modelo) {
        this.matricula = matricula;
        this.modelo = modelo;
    }

}
