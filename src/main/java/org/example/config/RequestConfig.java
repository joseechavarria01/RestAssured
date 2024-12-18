package org.example.config;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import org.example.utils.enums.LogsRestAssured;

import java.io.OutputStream;
import java.io.PrintStream;

public class RequestConfig {

    /**
     * Configura contenttype.
     */
    public static void contentType(String contentType) {
        RestAssured.requestSpecification = RestAssured.given().contentType(contentType);
    }

    public static void header(String key, String value) {
        RestAssured.requestSpecification = RestAssured.given().header(key, value);
    }


    /**
     * Configura si se muestran los logs
     * @param enable
     */
    public static void enableLogs(LogsRestAssured enable) {
        switch (enable) {
            case RESPONSE_ALL ->  RestAssured.requestSpecification.response().log().all();
            case RESPONSE_BODY ->  RestAssured.requestSpecification.response().log().body();
            case RESPONSE_HEADERS ->  RestAssured.requestSpecification.response().log().headers();
            case RESPONSE_IFVALIDATIONFAILS -> RestAssured.requestSpecification.response().log().ifValidationFails();
            case REQUEST_ALL ->  RestAssured.requestSpecification.request().log().all();
            case REQUEST_BODY ->  RestAssured.requestSpecification.request().log().body();
            case REQUEST_HEADERS ->  RestAssured.requestSpecification.request().log().headers();
            case REQUEST_IFVALIDATIONFAILS -> RestAssured.requestSpecification.request().log().ifValidationFails();
            case DISABLE_ALL -> {
                RestAssured.config = RestAssured.config().logConfig(new LogConfig(new PrintStream(new OutputStream() {
                    @Override
                    public void write(int b) {
                        // Do nothing
                    }
                }), true));
            }
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

