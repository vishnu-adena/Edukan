package com.adena.edhukanuserservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeDTO {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    private String email;
}
