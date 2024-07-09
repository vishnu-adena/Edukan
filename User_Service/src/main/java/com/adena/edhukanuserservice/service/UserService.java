package com.adena.edhukanuserservice.service;

import com.adena.edhukanuserservice.DTOs.PasswordChangeDTO;
import com.adena.edhukanuserservice.DTOs.SignUpResponseDTO;
import com.adena.edhukanuserservice.exceptions.InvalidPasswordException;
import com.adena.edhukanuserservice.exceptions.UserNotFoundException;
import com.adena.edhukanuserservice.models.Users;
import com.adena.edhukanuserservice.respository.UserRepository;

import com.adena.edhukanuserservice.utils.JwtTokenProvider;
import com.nimbusds.jose.JOSEException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
public class UserService implements IUserService {


    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtTokenProvider jwtTokenProvider;
//    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public  UserService(UserRepository userRepository, BCryptPasswordEncoder encoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.encoder = encoder;
  //      this.jwtTokenUtil = jwtTokenUtil;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public  boolean changePassword(PasswordChangeDTO passwordValue) throws InvalidPasswordException {

        Optional<Users> usersOptional = userRepository.findByEmail(passwordValue.getEmail());
        if (usersOptional.isEmpty()) {
            throw new InvalidPasswordException("User Not found SignUp");
        }
        Users users = usersOptional.get();
        if (!encoder.matches(passwordValue.getNewPassword(), users.getHashedPassword()) && (Objects.equals(passwordValue.getNewPassword(), passwordValue.getConfirmPassword()))) {
            throw new InvalidPasswordException("Wrong Password");
        }
        users.setHashedPassword(encoder.encode(passwordValue.getNewPassword()));

        return true;
    }

    @Override
    public SignUpResponseDTO getUser(String Token) throws UserNotFoundException, ParseException, JOSEException {
        Claims jwtClaims = jwtTokenProvider.getClaimsFromToken(Token);
        int userId = (int) jwtClaims.get("userId");

        Optional<Users> userOptional = userRepository.findById(Long.valueOf(userId));
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User Not found SignUp");
        }
        SignUpResponseDTO signUpResponseDTO = new SignUpResponseDTO(userOptional);
        return signUpResponseDTO;
    }
}
