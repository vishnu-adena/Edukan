package com.adena.edhukanuserservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "userservice")
public class Role extends BaseModel {
    private String roleName;
}
