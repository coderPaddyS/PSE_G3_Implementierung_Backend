package de.itermori.pse.kitroomfinder.backend.services;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import de.itermori.pse.kitroomfinder.backend.exceptions.BadTokenException;
import de.itermori.pse.kitroomfinder.backend.exceptions.UserNotFoundException;
import de.itermori.pse.kitroomfinder.backend.models.User;
import de.itermori.pse.kitroomfinder.backend.repositories.UserRepository;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides a service for the model {@link User}.
 * Implements the service interface {@link UserService} which defines
 * the corresponding GraphQL schema methods related to the model {@link User}.
 * Uses the repository {@link UserRepository}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;

    /**
     * The constructor which initializes the alias service implementation
     * with the required repositories.
     *
     * @param userRepository    The required {@link UserRepository}.
     */
    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     */
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
            return username;
        }
        userRepository.save(new User(username,"USER"));
        return username;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isAdmin() {
        return true;
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
