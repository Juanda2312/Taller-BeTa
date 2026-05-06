package co.edu.uniquindio.tallerbeta.util;

import co.edu.uniquindio.tallerbeta.model.Enum.Especializacion;
import co.edu.uniquindio.tallerbeta.model.Enum.Estado;
import co.edu.uniquindio.tallerbeta.model.entity.*;
import co.edu.uniquindio.tallerbeta.repository.*;

import java.io.File;

public class DatosDePrueba {

    public static void cargar() {
        // Solo carga si no existen los archivos
        if (new File(Constantes.RUTA_CLIENTE).exists()) return;

        new File("data").mkdirs();

        cargarClientes();
        cargarMecanicos();
        cargarOrdenes();
    }

    private static void cargarClientes() {
        ClienteRepositorio repo = new ClienteRepositorio();

        repo.registrarCliente(new Cliente("Juan Tapiero",    "1094901966", "juan@gmail.com",    "juan123"));
        repo.registrarCliente(new Cliente("Jose Bedoya",     "1114152806", "jose@gmail.com",    "jose123"));
        repo.registrarCliente(new Cliente("Maria Lopez",     "1000111222", "maria@gmail.com",   "maria123"));
        repo.registrarCliente(new Cliente("Carlos Reyes",    "1000333444", "carlos@gmail.com",  "carlos123"));
        repo.registrarCliente(new Cliente("Ana Gutierrez",   "1000555666", "ana@gmail.com",     "ana123"));
        repo.registrarCliente(new Cliente("Pedro Morales",   "1000777888", "pedro@gmail.com",   "pedro123"));
        repo.registrarCliente(new Cliente("Laura Castillo",  "1000999000", "laura@gmail.com",   "laura123"));
        repo.registrarCliente(new Cliente("Andres Herrera",  "1001111222", "andres@gmail.com",  "andres123"));
        repo.registrarCliente(new Cliente("Sofia Vargas",    "1001333444", "sofia@gmail.com",   "sofia123"));
        repo.registrarCliente(new Cliente("Diego Ramirez",   "1001555666", "diego@gmail.com",   "diego123"));
    }

    private static void cargarMecanicos() {
        MecanicoRepositorio repo = new MecanicoRepositorio();

        repo.registrarMecanico(new Mecanico("Luis Perez",     "2001111111", Especializacion.AUTOMOTRIZ));
        repo.registrarMecanico(new Mecanico("Ricardo Gomez",  "2002222222", Especializacion.ELECTRICO));
        repo.registrarMecanico(new Mecanico("Fernando Diaz",  "2003333333", Especializacion.DIESEL));
        repo.registrarMecanico(new Mecanico("Camilo Torres",  "2004444444", Especializacion.AUTOMOTRIZ));
        repo.registrarMecanico(new Mecanico("Julian Ospina",  "2005555555", Especializacion.ELECTRICO));
    }

    private static void cargarOrdenes() {
        ClienteRepositorio  clienteRepo  = new ClienteRepositorio();
        MecanicoRepositorio mecanicoRepo = new MecanicoRepositorio();
        OrdenRepositorio    ordenRepo    = new OrdenRepositorio();

        Cliente juan   = clienteRepo.listarClientes().get(0);
        Cliente jose   = clienteRepo.listarClientes().get(1);
        Cliente maria  = clienteRepo.listarClientes().get(2);
        Cliente carlos = clienteRepo.listarClientes().get(3);
        Cliente ana    = clienteRepo.listarClientes().get(4);

        Mecanico luis     = mecanicoRepo.listarMecanicos().get(0);
        Mecanico ricardo  = mecanicoRepo.listarMecanicos().get(1);

        // Órdenes de Juan
        Orden o1 = new Orden("Cambio de aceite y filtros", juan);
        Orden o2 = new Orden("Revisión de frenos delanteros", juan);
        o2.setMecanico(luis);
        o2.setEstado(Estado.ENPROCESO);
        luis.setOrdenAsignada(o2);

        // Órdenes de Jose
        Orden o3 = new Orden("Falla en el sistema eléctrico", jose);
        o3.setMecanico(ricardo);
        o3.setEstado(Estado.FINALIZADO);
        Orden o4 = new Orden("Ruido en la caja de cambios", jose);

        // Órdenes de Maria
        Orden o5 = new Orden("Alineación y balanceo", maria);
        Orden o6 = new Orden("Cambio de batería", maria);
        o6.setEstado(Estado.SINASIGNAR);

        // Órdenes de Carlos
        Orden o7 = new Orden("Revisión general del motor", carlos);
        Orden o8 = new Orden("Cambio de correa de distribución", carlos);

        // Órdenes de Ana
        Orden o9  = new Orden("Cambio de pastillas de freno", ana);
        Orden o10 = new Orden("Revisión de suspensión delantera", ana);
        Orden o11 = new Orden("Cambio de llantas", ana);

        ordenRepo.registrarOrden(o1);
        ordenRepo.registrarOrden(o2);
        ordenRepo.registrarOrden(o3);
        ordenRepo.registrarOrden(o4);
        ordenRepo.registrarOrden(o5);
        ordenRepo.registrarOrden(o6);
        ordenRepo.registrarOrden(o7);
        ordenRepo.registrarOrden(o8);
        ordenRepo.registrarOrden(o9);
        ordenRepo.registrarOrden(o10);
        ordenRepo.registrarOrden(o11);

        // Actualizar mecánicos con orden asignada
        mecanicoRepo.guardarDatos(mecanicoRepo.listarMecanicos());
    }
}