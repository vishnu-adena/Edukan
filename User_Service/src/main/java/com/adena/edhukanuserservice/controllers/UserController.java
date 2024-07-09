package com.adena.edhukanuserservice.controllers;

import com.adena.edhukanuserservice.DTOs.*;
import com.adena.edhukanuserservice.exceptions.InvalidPasswordException;
import com.adena.edhukanuserservice.exceptions.UserNotFoundException;
import com.adena.edhukanuserservice.service.UserService;
import com.nimbusds.jose.JOSEException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Object> changePassword(@RequestHeader Map<String, String> headers, @RequestBody PasswordChangeDTO passwordChangeDTO) throws InvalidPasswordException, UserNotFoundException, ParseException, JOSEException {

        String token = "";
        String authorization = headers.get("authorization");
        if (Objects.equals(authorization, null)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
        }

        SignUpResponseDTO claims = userService.getUser(token);
        if (claims == null)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        boolean response = userService.changePassword(passwordChangeDTO);
        if (response)return new ResponseEntity<>("Password Changed Successfully", HttpStatus.OK);
        else return new ResponseEntity<>("Password Changed Failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestHeader Map<String, String> headers) throws UserNotFoundException, ParseException, JOSEException {

        String token = "";
        String authorization = headers.get("authorization");
        if (Objects.equals(authorization, null)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
        }

        SignUpResponseDTO responseDTO= userService.getUser(token);

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);

    }


}
