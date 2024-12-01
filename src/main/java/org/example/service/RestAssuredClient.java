package org.example.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.dto.RequestDTO;
import org.example.dto.ResponseDTO;

public class RestAssuredClient {

    private String baseUri;

    public RestAssuredClient() {
    }

    public <T extends ResponseDTO> T get(String endpoint, Class<T> responseClass) {
        Response response = RestAssured.get(baseUri + endpoint);
        return response.as(responseClass);
    }

    public <T extends ResponseDTO, U extends RequestDTO> T post(String endpoint, U requestBody, Class<T> responseClass) {
        Response response = RestAssured.given()
                .body(requestBody)
                .post(endpoint);

        if (response.statusCode() == 200) {
            return response.as(responseClass);
        } else {
            throw new RuntimeException("Error en la respuesta: " + response.statusCode());
        }
    }

    // Métodos para otros tipos de solicitudes (PUT, DELETE, etc.) pueden agregarse de manera similar
}

