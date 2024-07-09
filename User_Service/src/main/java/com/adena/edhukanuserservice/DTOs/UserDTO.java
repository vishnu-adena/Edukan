package com.adena.edhukanuserservice.DTOs;

import com.adena.edhukanuserservice.models.Role;
import com.adena.edhukanuserservice.models.Users;

import java.util.List;

public class UserDTO {
    private long userId;
    private String name;
    private String email;
    private List<Role> roles;


    public static UserDTO fromUser(Users user) {
        UserDTO userDTO = new UserDTO();
        userDTO.userId = user.getId();
        userDTO.name = user.getName();
        userDTO.email = user.getEmail();
        userDTO.roles = user.getRoles();
        return userDTO;
    }
}
