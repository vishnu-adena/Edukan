package com.adena.edhukanuserservice.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class AuthResponse {
    private Map<String, String> token;

    public AuthResponse(Map<String, String> token) {
        this.token = token;
    }

}
