package de.itermori.pse.kitroomfinder.backend.services;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import de.itermori.pse.kitroomfinder.backend.Exceptions.UserAlreadyRegisteredException;
import de.itermori.pse.kitroomfinder.backend.Exceptions.UserNotFoundException;
import de.itermori.pse.kitroomfinder.backend.models.User;
import de.itermori.pse.kitroomfinder.backend.repositories.UserRepository;
import de.itermori.pse.kitroomfinder.backend.Exceptions.BadTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.auth0.jwt.JWTVerifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.util.*;

@Service
public class UserServiceImp implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public String addUser(String accessToken) {
        Optional<DecodedJWT> decodedJWT = verifyAndDecodeToken(accessToken);
        if (decodedJWT.isEmpty()) {
            throw new BadTokenException();
        }
        DecodedJWT castedDecodedJWT = decodedJWT.get();
        String username = castedDecodedJWT.getClaim("preferred_username").asString();
        if (userRepository.findByName(username) != null) {
            throw new UserAlreadyRegisteredException();
        }
        userRepository.save(new User(username,"USER"));
        return username;
    }

    @Override
    public User loadUserByToken(String accessToken) {
        Optional<DecodedJWT> decodedJWT = verifyAndDecodeToken(accessToken);
        if (decodedJWT.isEmpty()) {
            throw new BadTokenException();
        }
        DecodedJWT castedDecodedJWT = decodedJWT.get();
        String username = castedDecodedJWT.getClaim("preferred_username").asString();
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    private Optional<DecodedJWT> verifyAndDecodeToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            JwkProvider provider = new UrlJwkProvider(new URL("https://oidc.scc.kit.edu/auth/realms/kit/protocol/openid-connect/certs"));
            Jwk jwk = provider.get(decodedJWT.getKeyId());
            JWTVerifier verifier = JWT
                    .require(Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null))
                    .build();
            return Optional.of(verifier.verify(token));
        } catch(JWTVerificationException | MalformedURLException | JwkException ex) {
            try {
                JWTVerifier verifier = JWT
                                            .require(Algorithm.HMAC256("secret"))
                                            .withIssuer("my-graphql-api") //TODO
                                            .build();
                return Optional.of(verifier.verify(token));
            } catch(JWTVerificationException exx) {
                return Optional.empty();
            }
        }
    }
}
