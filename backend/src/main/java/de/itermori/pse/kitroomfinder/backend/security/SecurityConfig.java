package de.itermori.pse.kitroomfinder.backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    @Bean
    public Algorithm jwtAlgorithm() {
        return Algorithm.HMAC256("secret"); //TODO put right Algorithm and key here
    }

    @Bean
    public JWTVerifier verifier(Algorithm algorithm) {
        return JWT
                .require(algorithm)
                .withIssuer() //TODO
                .build();
    }

}