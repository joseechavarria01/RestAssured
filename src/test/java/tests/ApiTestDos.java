package tests;

import org.example.dto.UserRequestDTO;
import org.example.dto.UserResponseDTO;
import org.example.service.RestAssuredClient;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;


public class ApiTestDos extends BaseTest{

    UserRequestDTO userRequest = new UserRequestDTO();
    RestAssuredClient client = new RestAssuredClient();

    @Test
    public void testPost3() {

        test.pass("Se inicializa la instancia del usuario.");
        userRequest.setEmail("eve.holt@reqres.in");
        userRequest.setPassword("cityslicka");

        test.pass(String.format("Se Realiza el intento de login %s", userRequest.toString()));
        UserResponseDTO userResponse = client.post("/login", userRequest, UserResponseDTO.class);

        test.pass(String.format("Se valida el token %s", userResponse.getToken()));
        assertThat(userResponse.getToken(), equalToIgnoringCase("QpwL5tke4Pnpja7X4"));

        test.pass("Test passed successfully");
    }
}