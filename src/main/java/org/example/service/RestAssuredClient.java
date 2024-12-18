package org.example.service;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.utils.JsonConvert;

import java.util.Map;

public class RestAssuredClient {

    public RestAssuredClient() {
    }
/*
    public <T> T get(String endpoint, Class<T> responseClass) {
        Response response = RestAssured.get(endpoint);
        return JsonConvert.deserialize(response.getBody().asString(),responseClass);
    }
*/
    public Response get(String endpoint) {
        Response response = RestAssured.given().get(endpoint);
        return response;
    }

    public <T, U> T post(String endpoint, U requestBody, Class<T> responseClass) {
        Response response = RestAssured.given()
                .body(requestBody)
                .post(endpoint);
        return response.as(responseClass);
    }

    public <T> T post(String endpoint, Map<String, String> formData, Class<T> responseClass) {

        var requestSpec = RestAssured.given(); // Construir la solicitud
        formData.forEach(requestSpec::multiPart); // Agregar los datos del HashMap
        Response response = requestSpec.when().post(endpoint); // Enviar la solicitud
       // response.then().log().all();

      return JsonConvert.deserialize(response.getBody().asString(),responseClass);
    }

    // Métodos para otros tipos de solicitudes (PUT, DELETE, etc.) pueden agregarse de manera similar
}

