package com.adena.edhukanuserservice.respository;

import com.adena.edhukanuserservice.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Users, Long>{


    public Optional<Users> findByEmail(String email);

}