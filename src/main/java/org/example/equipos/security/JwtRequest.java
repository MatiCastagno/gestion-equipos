package org.example.equipos.security;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;

}
