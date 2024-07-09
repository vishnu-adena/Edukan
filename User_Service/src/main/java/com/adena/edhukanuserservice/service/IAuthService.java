package com.adena.edhukanuserservice.service;

import com.adena.edhukanuserservice.DTOs.LoginRequestDTO;
import com.adena.edhukanuserservice.exceptions.UserAlreadyPresent;
import com.adena.edhukanuserservice.models.Users;

public interface IAuthService {
    Users signUp(String name, String email, String password) throws UserAlreadyPresent;

    String login(LoginRequestDTO loginRequest) throws Exception;
}
