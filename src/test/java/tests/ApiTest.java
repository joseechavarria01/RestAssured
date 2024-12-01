package tests;

import org.example.dto.UserRequestDTO;
import org.example.dto.UserResponseDTO;
import org.example.service.RestAssuredClient;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;


public class ApiTest extends BaseTest{

    UserRequestDTO request = new UserRequestDTO();
    RestAssuredClient client = new RestAssuredClient();

    @Test
    public void testPost() {
        request.setEmail("eve.holt@reqres.in");
        request.setPassword("cityslicka");

        UserResponseDTO userResponse = client.post("/login", request, UserResponseDTO.class);
        System.out.println("token: " + userResponse.getToken());

        assertThat(userResponse.getToken(), equalToIgnoringCase("QpwL5tke4Pnpja7X4"));
        test.pass("Test passed successfully");
    }

    @Test
    public void testPost2() {
        request.setEmail("eve.holt@reqres.in");
        request.setPassword("cityslicka");

        UserResponseDTO userResponse = client.post("/login", request, UserResponseDTO.class);
        System.out.println("token: " + userResponse.getToken());

        assertThat(userResponse.getToken(), equalToIgnoringCase("QpwL5tke4Pnpja7X4"));
        test.pass("Test passed successfully");
    }
}