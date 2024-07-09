package com.adena.edhukanuserservice.service;

import com.adena.edhukanuserservice.DTOs.LoginRequestDTO;
import com.adena.edhukanuserservice.exceptions.UserAlreadyPresent;
import com.adena.edhukanuserservice.models.Users;
import com.adena.edhukanuserservice.respository.UserRepository;
import com.adena.edhukanuserservice.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService  implements IAuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Users signUp(String name, String email, String password) throws UserAlreadyPresent {
        Optional<Users> usersOptional = userRepository.findByEmail(email);
        if (usersOptional.isPresent()) {
            throw new UserAlreadyPresent("User already Present With this Email "+email);
        }

        Users user = new Users();
        user.setEmail(email);
        user.setEmail(email);
        user.setName(name);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        Users savedUser =  userRepository.save(user);
        return savedUser;
    };

    @Override
    public String login(LoginRequestDTO loginRequest) throws Exception{

        Optional<Users> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isEmpty()){
            throw new Exception("User not found");
        }
        if(!bCryptPasswordEncoder.matches(loginRequest.getPassword(),user.get().getHashedPassword())){
            throw new Exception("Wrong password");
        }
        String response = jwtTokenProvider.generateToken(user.get().getEmail());
        return response;
    }

}
