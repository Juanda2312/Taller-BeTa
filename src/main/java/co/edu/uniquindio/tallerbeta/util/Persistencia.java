package co.edu.uniquindio.tallerbeta.util;

import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.util.List;

/**
 * Clase utilitaria para la persistencia de datos utilizando serialización XML con Jackson.
 * Proporciona métodos para serializar objetos y deserializar objetos y listas desde archivos XML.
 */
public class Persistencia {

    /**
     * Método que permite serializar un objeto en un archivo XML en la ruta especificada.
     *
     * @param ruta La ruta del archivo donde se serializará el objeto.
     * @param objeto El objeto a serializar.
     * @throws IOException Si ocurre un error durante la escritura del archivo.
     */
    public static void serializarObjeto(String ruta, Object objeto) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.writeValue(new File(ruta), objeto);
    }

    /**
     * Método que permite deserializar un objeto desde un archivo XML en la ruta especificada.
     *
     * @param ruta La ruta del archivo desde donde se deserializará el objeto.
     * @param clase La clase del objeto a deserializar.
     * @param <T> El tipo del objeto.
     * @return El objeto deserializado, o null si el archivo no existe.
     * @throws Exception Si ocurre un error durante la deserialización.
     */
    public static <T> Object deserializarObjeto(String ruta, Class<T> clase) throws Exception{

        if(!new File(ruta).exists()){
            return null;
        }

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        return xmlMapper.readValue(ruta, clase);
    }

    /**
     * Método que permite deserializar una lista de objetos desde un archivo XML en la ruta especificada.
     *
     * @param ruta La ruta del archivo desde donde se deserializará la lista.
     * @param clase La clase de los elementos de la lista.
     * @param <T> El tipo de los elementos de la lista.
     * @return La lista deserializada, o null si el archivo no existe.
     * @throws IOException Si ocurre un error durante la deserialización.
     */
    public static <T> List<T> deserializarLista(String ruta, Class<T> clase) throws IOException {
        if (!new File(ruta).exists()) {
            return null;
        }

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        CollectionType listType = xmlMapper.getTypeFactory().constructCollectionType(List.class, clase);
        return xmlMapper.readValue(new File(ruta), listType);
    }

}