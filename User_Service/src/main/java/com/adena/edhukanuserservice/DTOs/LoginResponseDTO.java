package com.adena.edhukanuserservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private String token;
    private String message;

}
