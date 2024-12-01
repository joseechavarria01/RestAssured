package org.example.config;

import io.restassured.RestAssured;
import org.example.utils.enums.LogsRestAssured;

public class RequestConfig {

    /**
     * Configura contenttype.
     */
    public static void contentType(String contentType) {
        RestAssured.requestSpecification = RestAssured.given().contentType(contentType);
    }

    /**
     * Configura si se muestran los logs
     * @param enable
     */
    public static void enableLogs(LogsRestAssured enable) {

        switch (enable) {
            case ALL ->  RestAssured.requestSpecification = RestAssured.given().log().all();
            case BODY -> RestAssured.requestSpecification = RestAssured.given().log().body();
            case HEADERS -> RestAssured.requestSpecification = RestAssured.given().log().headers();
            case IFVALIDATIONFAILS -> RestAssured.requestSpecification = RestAssured.given().log().ifValidationFails();
        }
    }

    /**
     * Configura el host y el path de la url
     * @param uri
     * @param basePath
     */
    public static void baseUri(String uri, String basePath) {
        RestAssured.baseURI = uri;
        RestAssured.basePath = basePath;
    }
}

