package co.edu.uniquindio.tallerbeta.model.entity;

import co.edu.uniquindio.tallerbeta.model.Enum.Especializacion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mecanico extends Persona{
    Especializacion especializacion;
    Orden ordenAsignada;

    public Mecanico(String nombre, String cedula, Especializacion especializacion) {
        super(nombre, cedula);
        this.especializacion = especializacion;
        this.ordenAsignada = null;
    }

    public double hacerPresupuesto(){
        return 0;
    }

    public Orden realizarMantenimiento(){
        return null;
    }
}
