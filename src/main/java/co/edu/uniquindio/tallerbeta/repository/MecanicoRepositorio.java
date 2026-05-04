package co.edu.uniquindio.tallerbeta.repository;

import co.edu.uniquindio.tallerbeta.model.entity.Mecanico;
import co.edu.uniquindio.tallerbeta.util.Constantes;
import co.edu.uniquindio.tallerbeta.util.Persistencia;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MecanicoRepositorio {
    private final LinkedList<Mecanico> listaMecanicos;

    public MecanicoRepositorio() {
        this.listaMecanicos = leerDatos();
    }

    public void registrarMecanico(Mecanico mecanico) {
        listaMecanicos.add(mecanico);
        guardarDatos(listaMecanicos);
    }

    public void eliminarMecanico(Mecanico mecanico) {
        listaMecanicos.remove(mecanico);
        guardarDatos(listaMecanicos);
    }

    public LinkedList<Mecanico> listarMecanicos() {
        return listaMecanicos;
    }

    public void guardarDatos(LinkedList<Mecanico> mecanicos) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_MECANICO, mecanicos);
        } catch (IOException e) {
            System.err.println("Error guardando mecanicos: " + e.getMessage());
        }
    }

    public LinkedList<Mecanico> leerDatos() {
        try {
            List<Mecanico> datos = Persistencia.deserializarLista(Constantes.RUTA_MECANICO, Mecanico.class);
            if (datos != null) {
                return new LinkedList<>(datos);
            }
        } catch (Exception e) {
            System.err.println("Error cargando mecanicos: " + e.getMessage());
        }
        return new LinkedList<>();
    }
}