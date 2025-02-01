package com.wsconge.DTO;

public class LoginResponseDTO {
    private Long id;
    private String token;
    private String role;

    public LoginResponseDTO(String token, String username, Long id) {
        this.token = token;
        this.role = username;
        this.id=id;
    }

    public String getToken() {
        return token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String username) {
        this.role = username;
    }
}
