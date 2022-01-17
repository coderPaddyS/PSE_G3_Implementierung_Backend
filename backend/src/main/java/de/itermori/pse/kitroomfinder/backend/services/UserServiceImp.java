package de.itermori.pse.kitroomfinder.backend.services;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import de.itermori.pse.kitroomfinder.backend.models.User;
import de.itermori.pse.kitroomfinder.backend.security.BadTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.auth0.jwt.JWTVerifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImp implements UserService{

    private final JWTVerifier verifier;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(JWTVerifier verifier, UserRepository userRepository) {
        this.verifier = verifier;
        this.userRepository = userRepository;
    }

    @Override
    public String addUser(String accessToken) {

        String username = verifyAndDecodeToken(accessToken)
                .map(DecodedJWT::getSubject)
                .orElseThrow(BadTokenException::new);
        GrantedAuthority userAuthority = new SimpleGrantedAuthority("User");
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(userAuthority);
        userRepository.safe(new User(username,authorities));
        return username;
    }

    @Override
    public User loadUserByToken(String accessToken) {
        return verifyAndDecodeToken(accessToken)
                .map(DecodedJWT::getSubject)
                .flatMap(userRepository::findByName)
                .orElseThrow(BadTokenException::new);
    }

    private Optional<DecodedJWT> verifyAndDecodeToken(String token) {
        try {
            return Optional.of(verifier.verify(token));
        } catch(JWTVerificationException ex) {
            return Optional.empty();
        }
    }
}
