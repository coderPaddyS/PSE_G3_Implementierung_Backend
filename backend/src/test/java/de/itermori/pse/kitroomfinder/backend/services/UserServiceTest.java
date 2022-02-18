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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link UserService}.
 *
 * @author Lukas Zetto
 * @version 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private final String USER = "username";

    private final String token = JWT
            .create()
            .withIssuer("my-graphql-api")
            .withIssuedAt(Calendar.getInstance().getTime())
            .withExpiresAt(new Date(Calendar.getInstance().getTime().getTime() + 600000))
            .withClaim("preferred_username", USER)
            .sign(Algorithm.HMAC256("secret"));

    /**
     * Sets up the test resources.
     */
    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    /**
     * Tests the method {@link UserService#addUser(String)}.
     */
    @Test
    void addUserTest() {
        assertEquals(userService.addUser(token), USER);
        assertNotNull(userRepository.findByName(USER));
    }

    /**
     * Tests the method {@link UserService#loadUserByToken(String)}.
     */
    @Test
    void loadUserByTokenTest() {
        userRepository.save(new User(USER, "USER"));
        assertTrue(userService.loadUserByToken(token).getName().equals(USER) &&
                userService.loadUserByToken(token).getAuthorities().equals("USER"));
    }

    /**
     * Tests the method {@link UserService#loadUserByToken(String)}.
     */
    @Test
    void falseTokenTest() {
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

    /**
     * Tests the method {@link UserService#loadUserByToken(String)}.
     */
    @Test
    void userNotRegisteredTest() {
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
