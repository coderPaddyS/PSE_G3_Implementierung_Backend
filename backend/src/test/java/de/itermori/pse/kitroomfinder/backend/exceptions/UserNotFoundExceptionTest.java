package de.itermori.pse.kitroomfinder.backend.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link UserNotFoundException}.
 *
 * @author Adriano Castro
 * @version 1.0
 */
class UserNotFoundExceptionTest {

    private UserNotFoundException userNotFoundException;

    /**
     * Sets up the test resources.
     */
    @BeforeEach
    void setUp() {
        userNotFoundException = new UserNotFoundException();
    }

    /**
     * Tests the method {@link UserNotFoundException#getMessage()}.
     */
    @Test
    void testGetMessage() {
        String expected = "User not registered yet";
        assertEquals(expected, userNotFoundException.getMessage());
    }

}
