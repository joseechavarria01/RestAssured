package org.example.dto;

public class UserResponseDTO implements ResponseDTO {
    private String token;

    // Getters y setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}