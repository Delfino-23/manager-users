package com.manager.clients.payload;

import java.util.List;
/**
 * Classe de resposta JWT.
 * Contém o token JWT, o tipo de token, o email do usuário e suas funções.
 */
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String email;
    private List<String> roles;

    public JwtResponse(String token, String email, List<String> roles) {
        this.token = token;
        this.email = email;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }
}
