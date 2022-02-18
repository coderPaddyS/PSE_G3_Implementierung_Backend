package de.itermori.pse.kitroomfinder.backend.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import de.itermori.pse.kitroomfinder.backend.exceptions.BadTokenException;
import de.itermori.pse.kitroomfinder.backend.exceptions.UserNotFoundException;
import de.itermori.pse.kitroomfinder.backend.models.User;
import de.itermori.pse.kitroomfinder.backend.repositories.UserRepository;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    String USER = "username";

    String token = JWT
            .create()
            .withIssuer("my-graphql-api")
            .withIssuedAt(Calendar.getInstance().getTime())
            .withExpiresAt(new Date(Calendar.getInstance().getTime().getTime() + 600000))
            .withClaim("preferred_username", USER)
            .sign(Algorithm.HMAC256("secret"));

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
    }

    @Test
    public void addUserTest() {
        assertEquals(userService.addUser(token), USER);
        assertTrue(userRepository.findByName(USER) != null);
    }

    @Test
    public void loadUserByTokenTest() {
        userRepository.save(new User(USER, "USER"));
        assertTrue(userService.loadUserByToken(token).getName().equals(USER) &&
                userService.loadUserByToken(token).getAuthorities().equals("USER"));
    }

    @Test
    public void falseTokenTest() {
        String fakeToken = JWT
                .create()
                .withIssuer("my-graphql-api")
                .withIssuedAt(Calendar.getInstance().getTime())
                .withExpiresAt(new Date(Calendar.getInstance().getTime().getTime() + 600000))
                .withClaim("preferred_username", USER)
                .sign(Algorithm.HMAC256("falseSecret"));
        assertThrows(BadTokenException.class, () -> {
            userService.loadUserByToken(fakeToken);
        });
    }

    @Test
    public void userNotRegisteredTest() {
        String fakeToken = JWT
                .create()
                .withIssuer("my-graphql-api")
                .withIssuedAt(Calendar.getInstance().getTime())
                .withExpiresAt(new Date(Calendar.getInstance().getTime().getTime() + 600000))
                .withClaim("preferred_username", "false")
                .sign(Algorithm.HMAC256("secret"));
        assertThrows(UserNotFoundException.class, () -> {
            userService.loadUserByToken(fakeToken);
        });
    }
}
