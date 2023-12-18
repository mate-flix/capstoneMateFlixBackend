package com.mteflix.capstonemateflixbackend.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;






@Component
public class JwtService {


    public String generateAccessToken(String username){
        String token = JWT.create()
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(Duration.ofSeconds(86400)))
                .withIssuer("Mate Flix inc.")
                .withSubject(username)

                .sign(Algorithm.HMAC256("secret"));
        return token;
    }


    public String extractUsernameFrom(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("secret"))
                                  .withIssuer("Mate Flix inc.")
                                  .build();
       DecodedJWT decodedJWT =  verifier.verify(token);
       return decodedJWT.getSubject();
    }
}
