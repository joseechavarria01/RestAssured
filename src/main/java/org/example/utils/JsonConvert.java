package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonConvert {

    public static <T> T deserialize(String responseBody, Class<T> responseClass) {
        ObjectMapper mapper = new ObjectMapper(); // Usar ObjectMapper para deserializar manualmente
        try {
            return mapper.readValue(responseBody, responseClass);
        } catch (Exception e) {
            throw new IllegalStateException("No se pudo deserializar el objeto", e);
        }
    }

    public static <T> String serialize(T instance) {
        ObjectMapper mapper = new ObjectMapper(); // Usar ObjectMapper para deserializar manualmente
        try {
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            return mapper.writeValueAsString(instance);
        } catch (Exception e) {
            throw new IllegalStateException("No se pudo serializar la clase como JSON", e);
        }
    }
}
