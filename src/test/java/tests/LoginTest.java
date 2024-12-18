package tests;

import org.example.dto.respose.UserResponse;
import org.example.service.RestAssuredClient;
import org.testng.annotations.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

public class LoginTest extends BaseTest{

    RestAssuredClient client = new RestAssuredClient();

    @Test
    public void VerifyLogin_Ok() {

        Map<String, String> formData = new HashMap<>();

        reports.infoStep("Se inicializa los parametros del usuario.");
        formData.put("email","xavomawusse-1109@yopmail.com");
        formData.put("password","QngT0heiO99MGh");

        reports.infoStep(String.format("Se Realiza el intento de login: email: %s, password: %s", formData.get("email"), formData.get("password")));
        UserResponse userResponse = client.post("/verifyLogin", formData, UserResponse.class);

        reports.infoStep(String.format("Se valida el response code: %s", userResponse.getResponseCode()));
        assertThat(userResponse.getResponseCode(), is(200));

        reports.infoStep(String.format("Se valida el mensaje: %s", userResponse.getMessage()));
        assertThat(userResponse.getMessage(), equalToIgnoringCase("User exists!"));

        reports.infoStep("Test passed successfully");
    }

    @Test
    public void VerifyLoginInvalidDetails() {

        Map<String, String> formData = new HashMap<>();

        reports.infoStep("Se inicializa la instancia del usuario.");
        formData.put("email","xavomawusse-1109@yopmail.com");
        formData.put("password","QngT0heiO99MGh1");

        reports.infoStep(String.format("Se Realiza el intento de login: email: %s, password: %s", formData.get("email"), formData.get("password")));
        UserResponse userResponse = client.post("/verifyLogin", formData, UserResponse.class);

        reports.infoStep(String.format("Se valida el response code: %s", userResponse.getResponseCode()));
        assertThat(userResponse.getResponseCode(), is(404));

        reports.infoStep(String.format("Se valida el mensaje: %s", userResponse.getMessage()));
        assertThat(userResponse.getMessage(), equalToIgnoringCase("User not found!"));

        reports.infoStep("Test passed successfully");
    }
}