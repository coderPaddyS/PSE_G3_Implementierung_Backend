package de.itermori.pse.kitroomfinder.backend.services;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
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

    @Transactional
    @Override
    public String addUser(String accessToken) {

        String username = verifyAndDecodeToken(accessToken)
                .map(DecodedJWT::getSubject)
                .orElseThrow(BadTokenException::new);
        if (userRepository.findByName(username) == null) {
            throw new UserNotFoundException();
        }
        userRepository.save(new User(username,"USER"));
        return username;
    }

    @Override
    public User loadUserByToken(String accessToken) {
        Optional<String> usernameNullable = verifyAndDecodeToken(accessToken)
                .map(DecodedJWT::getSubject);
        if (usernameNullable.isEmpty()) {
            throw new BadTokenException();
        }
        String username = usernameNullable.get();
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not registered");
        }
        return user;
    }

    private Optional<DecodedJWT> verifyAndDecodeToken(String token) {
        try {
            return Optional.of(verifier.verify(token));
        } catch(JWTVerificationException ex) {
            return Optional.empty();
        }
    }
}
