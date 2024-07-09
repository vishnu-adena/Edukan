package com.adena.edhukanuserservice.utils;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.text.ParseException;

public class TokenUtils {
    private static String getUserIdFromRSAToken(String token) throws ParseException, ParseException {
        JWSObject jwsObject = JWSObject.parse(token);
        JWTClaimsSet claims = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
        return claims.getSubject(); // Assuming the user ID is stored in the "sub" (subject) claim
    }

    // Method to extract the token from the security context and get the user ID
    public static String getUserId() throws ParseException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String token = authentication.getToken().getTokenValue();
        return getUserIdFromRSAToken(token);
    }
}
