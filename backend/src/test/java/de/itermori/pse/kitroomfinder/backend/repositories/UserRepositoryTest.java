package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link UserRepository}.
 *
 * @author Adriano Castro
 * @version 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    /**
     * Sets the test resources up.
     */
    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    /**
     * Tests the method {@link UserRepository#findByName(String)}.
     */
    @Test
    void whenUserSaved_thenFindByName() {
        // save User to the database
        User expectedUser = new User("Name", "USER");
        userRepository.save(expectedUser);

        // check if saved user is found
        User actualUser = userRepository.findByName(expectedUser.getName());
        assertEquals(expectedUser, actualUser);
    }

}
