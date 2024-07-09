package com.adena.edhukanuserservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "userservice")
public class Users extends BaseModel{
    private String name;
    private String email;
    private String hashedPassword;
    private boolean isEmailVerified;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

}