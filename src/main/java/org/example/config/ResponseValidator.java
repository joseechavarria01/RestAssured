package org.example.config;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class ResponseValidator {

    /**
     * Valida el código de estado de la respuesta.
     *
     * @param response Respuesta a validar.
     * @param expectedStatusCode Código de estado esperado.
     */
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        response.then()
                .log().all()
                .statusCode(expectedStatusCode); // Valida el código de estado
    }

    /**
     * Valida que un campo específico no sea nulo.
     *
     * @param response Respuesta a validar.
     * @param field Campo que debe estar presente.
     */
    public static void validateFieldNotNull(Response response, String field) {
        response.then()
                .body(field, notNullValue()); // Valida que el campo no sea nulo
    }
}

