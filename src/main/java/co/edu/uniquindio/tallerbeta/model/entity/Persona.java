package co.edu.uniquindio.tallerbeta.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase base que representa a una persona en el sistema.
 * Contiene atributos comunes como nombre y cédula.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    private String nombre,cedula;

}
