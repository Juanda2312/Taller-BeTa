package co.edu.uniquindio.tallerbeta.repository;

import co.edu.uniquindio.tallerbeta.model.entity.Recepcionista;
import co.edu.uniquindio.tallerbeta.util.Constantes;
import co.edu.uniquindio.tallerbeta.util.Persistencia;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RecepcionistaRepositorio {
    private final LinkedList<Recepcionista> listaRecepcionistas;

    public RecepcionistaRepositorio() {
        this.listaRecepcionistas = leerDatos();
    }

    public void registrarRecepcionista(Recepcionista recepcionista) {
        listaRecepcionistas.add(recepcionista);
        guardarDatos(listaRecepcionistas);
    }

    public void eliminarRecepcionista(Recepcionista recepcionista) {
        listaRecepcionistas.remove(recepcionista);
        guardarDatos(listaRecepcionistas);
    }

    public LinkedList<Recepcionista> listarRecepcionistas() {
        return listaRecepcionistas;
    }

    public void guardarDatos(LinkedList<Recepcionista> recepcionistas) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_RECEPCIONISTA, recepcionistas);
        } catch (IOException e) {
            System.err.println("Error guardando recepcionistas: " + e.getMessage());
        }
    }

    public LinkedList<Recepcionista> leerDatos() {
        try {
            List<Recepcionista> datos = Persistencia.deserializarLista(Constantes.RUTA_RECEPCIONISTA, Recepcionista.class);
            if (datos != null) {
                return new LinkedList<>(datos);
            }
        } catch (Exception e) {
            System.err.println("Error cargando recepcionistas: " + e.getMessage());
        }
        return new LinkedList<>();
    }
}