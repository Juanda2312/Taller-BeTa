package co.edu.uniquindio.tallerbeta.service.Interface;

import co.edu.uniquindio.tallerbeta.model.entity.Cliente;
import co.edu.uniquindio.tallerbeta.model.entity.Mecanico;
import co.edu.uniquindio.tallerbeta.model.entity.Orden;

public interface ITallerServicio {

    //taller
    void registrarCliente(String nombre, String cedula, String correo, String contrasenia)throws Exception;
    void iniciarSesion(String correo,String contrasenia)throws Exception;

    //cliente
    Orden realizarOrden(String instrucciones, Cliente cliente);

    //recepcionista
    void atenderCliente(Cliente cliente);

    //mecanico
    double hacerPresupuesto();
    Orden realizarMantenimiento();

    //administrador
    Mecanico AsignarMecanico();

}
