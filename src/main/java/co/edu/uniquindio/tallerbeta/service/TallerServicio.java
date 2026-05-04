package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.model.entity.Cliente;
import co.edu.uniquindio.tallerbeta.model.entity.Mecanico;
import co.edu.uniquindio.tallerbeta.model.entity.Orden;
import co.edu.uniquindio.tallerbeta.service.Interface.ITallerServicio;

public class TallerServicio implements ITallerServicio {

    public void registrarCliente(String nombre, String cedula, String correo, String contrasenia) throws Exception {

    }

    public void iniciarSesion(String correo, String contrasenia) throws Exception {

    }

    public Orden realizarOrden(String instrucciones, Cliente cliente) {
        return null;
    }

    public void atenderCliente(Cliente cliente) {

    }

    public double hacerPresupuesto() {
        return 0;
    }

    public Orden realizarMantenimiento() {
        return null;
    }

    public Mecanico AsignarMecanico() {
        return null;
    }
}
