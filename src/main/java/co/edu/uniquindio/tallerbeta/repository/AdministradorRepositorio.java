package co.edu.uniquindio.tallerbeta.repository;

import co.edu.uniquindio.tallerbeta.model.entity.Administrador;
import co.edu.uniquindio.tallerbeta.util.Constantes;
import co.edu.uniquindio.tallerbeta.util.Persistencia;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AdministradorRepositorio {
    private final LinkedList<Administrador> listaAdministradores;

    public AdministradorRepositorio() {
        this.listaAdministradores = leerDatos();
    }

    public void registrarAdministrador(Administrador administrador) {
        listaAdministradores.add(administrador);
        guardarDatos(listaAdministradores);
    }

    public void eliminarAdministrador(Administrador administrador) {
        listaAdministradores.remove(administrador);
        guardarDatos(listaAdministradores);
    }

    public LinkedList<Administrador> listarAdministradores() {
        return listaAdministradores;
    }

    public void guardarDatos(LinkedList<Administrador> administradores) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_ADMINISTRADOR, administradores);
        } catch (IOException e) {
            System.err.println("Error guardando administradores: " + e.getMessage());
        }
    }

    public LinkedList<Administrador> leerDatos() {
        try {
            List<Administrador> datos = Persistencia.deserializarLista(Constantes.RUTA_ADMINISTRADOR, Administrador.class);
            if (datos != null) {
                return new LinkedList<>(datos);
            }
        } catch (Exception e) {
            System.err.println("Error cargando administradores: " + e.getMessage());
        }
        return new LinkedList<>();
    }
}