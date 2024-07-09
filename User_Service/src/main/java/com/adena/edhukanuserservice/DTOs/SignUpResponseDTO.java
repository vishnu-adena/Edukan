package com.adena.edhukanuserservice.DTOs;

import com.adena.edhukanuserservice.models.Users;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class SignUpResponseDTO {

    private String name;
    private String email;

    public SignUpResponseDTO(Optional<Users> optionalUser) {
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            this.name = user.getName();
            this.email = user.getEmail();
        }
    }
}
