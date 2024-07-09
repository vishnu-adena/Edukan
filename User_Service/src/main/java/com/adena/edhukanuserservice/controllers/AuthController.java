package com.adena.edhukanuserservice.controllers;

import com.adena.edhukanuserservice.DTOs.*;
import com.adena.edhukanuserservice.exceptions.UserAlreadyPresent;
import com.adena.edhukanuserservice.models.Users;
import com.adena.edhukanuserservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth2")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public Users signUp(@RequestBody SignupRequestDTO signupRequestDTO) throws UserAlreadyPresent {

        Users user = authService.signUp(signupRequestDTO.getName(), signupRequestDTO.getEmail(), signupRequestDTO.getPassword());
        //SignUpResponseDTO signUpResponseDTO = SignUpResponseDTO.fromSignUpResponseDTO(user);
        return user;

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) throws Exception {

        String access_token = String.valueOf(authService.login(loginRequest));
        return ResponseEntity.ok(Map.of("access_token", access_token));
    }

}

