package co.edu.uniquindio.tallerbeta.service.Interface;

import co.edu.uniquindio.tallerbeta.model.entity.Cliente;
import co.edu.uniquindio.tallerbeta.model.entity.Mecanico;
import co.edu.uniquindio.tallerbeta.model.entity.Orden;

/**
 * Interfaz que define los servicios principales del taller.
 * Incluye operaciones para clientes, recepcionistas, mecánicos y administradores.
 */
public interface ITallerServicio {

    //taller
    /**
     * Registra un nuevo cliente en el sistema.
     *
     * @param nombre El nombre del cliente.
     * @param cedula La cédula del cliente.
     * @param correo El correo electrónico del cliente.
     * @param contrasenia La contraseña del cliente.
     * @throws Exception Si ocurre un error en el registro.
     */
    void registrarCliente(String nombre, String cedula, String correo, String contrasenia)throws Exception;

    /**
     * Inicia sesión para un cliente.
     *
     * @param correo El correo del cliente.
     * @param contrasenia La contraseña del cliente.
     * @throws Exception Si las credenciales son incorrectas.
     */
    void iniciarSesion(String correo,String contrasenia)throws Exception;

    //cliente
    /**
     * Permite a un cliente realizar una orden de mantenimiento.
     *
     * @param instrucciones Las instrucciones de la orden.
     * @param cliente El cliente que realiza la orden.
     * @return La orden creada.
     */
    Orden realizarOrden(String instrucciones, Cliente cliente);

    //recepcionista
    /**
     * Permite atender a un cliente.
     *
     * @param cliente El cliente a atender.
     */
    void atenderCliente(Cliente cliente);

    //mecanico
    /**
     * Calcula el presupuesto para una orden asignada a un mecánico disponible.
     *
     * @return El presupuesto calculado.
     */
    double hacerPresupuesto();

    /**
     * Realiza el mantenimiento de una orden asignada.
     *
     * @return La orden finalizada.
     */
    Orden realizarMantenimiento();

    //administrador
    /**
     * Asigna un mecánico disponible.
     *
     * @return El mecánico asignado.
     */
    Mecanico AsignarMecanico();

}
