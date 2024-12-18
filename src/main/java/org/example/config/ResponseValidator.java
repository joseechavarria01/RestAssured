package org.example.config;

import io.restassured.response.Response;
import org.example.utils.TestReports;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.*;

public class ResponseValidator {

    protected static TestReports reports = TestReports.getInstance();

    /**
     * Valida el código de estado de la respuesta.
     *
     * @param response Respuesta a validar.
     * @param expectedStatusCode Código de estado esperado.
     */
    public static void statusCode(Response response, int expectedStatusCode) {
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
    public static void fieldNotNull(Response response, String field) {
        response.then()
                .body(field, notNullValue()); // Valida que el campo no sea nulo
    }

    /**
     * Valida que la lista sea mayor a 0
     *
     * @param response Respuesta a validar.
     * @param field Validar que la lista no esté vacía.
     */
    public static void matchersGreaterThan(Response response, String field) {
        response.then()
                .body(field, Matchers.greaterThan(0));
    }

    /**
     * Valida que la lista sea mayor a 0
     *
     * @param response Respuesta a validar.
     * @param field Validar que la lista no esté vacía.
     */
    public static void matchersEqualTo(Response response, String field) {
        response.then()
                .body(field, Matchers.greaterThan(0));
    }

    /**
     * Valida que la lista sea mayor a 0
     *
     * @param response Respuesta a validar.
     * @param field Validar que la lista no esté vacía.
     */
    public static void fieldEqualTo(Response response, String field, int value) {
        response.then()
                .body(field, equalTo(value));
    }

    /**
     * Valida que la lista sea mayor a 0
     *
     * @param response Respuesta a validar.
     * @param field Validar que la lista no esté vacía.
     */
    public static void matchersEqualTo(Response response, String field, String value) {
        response.then()
                .body(field,Matchers.equalTo(value));
    }

    /**
     * Valida que todos los elementos de la lista
     *
     * @param response Respuesta a validar.
     * @param field Campo en la lista no sea nullo
     */
    public static void matchersEveryItem(Response response, String field) {

        try {
            response.then()
                    .body(field, Matchers.everyItem(Matchers.notNullValue()));
            reports.infoStep("✔ Validación exitosa: Todos los elementos tienen el campo " + field);
        } catch (AssertionError e) {
            reports.failStep("✖ Validación fallida: Algún elementos no el campo " + field);
            throw e;
        }
    }
}