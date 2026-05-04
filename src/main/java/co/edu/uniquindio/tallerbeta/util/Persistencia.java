package co.edu.uniquindio.tallerbeta.util;

import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.util.List;


public class Persistencia {

    /**
     * Metodo que permite serializar un objeto en un archivo en la ruta especificada
     * @param objeto Objeto a serializar
     * @throws IOException
     */
    public static void serializarObjeto(String ruta, Object objeto) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.writeValue(new File(ruta), objeto);
    }


    /**
     * Metodo que permite deserializar un objeto de un archivo en la ruta especificada
     * @return Objeto deserializado
     * @throws Exception
     */
    public static <T> Object deserializarObjeto(String ruta, Class<T> Class) throws Exception{

        if(!new File(ruta).exists()){
            return null;
        }

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        return xmlMapper.readValue(ruta, Class);
    }

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