package com.adena.edhukanuserservice.utils;

import com.nimbusds.jose.JOSEException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public Claims getUserIdFromToken(String Token) throws JOSEException {

        Claims jwtClaims = jwtTokenProvider.getClaimsFromToken(Token);

        return jwtClaims;
    }
}
