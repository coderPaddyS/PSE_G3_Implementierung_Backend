package de.itermori.pse.kitroomfinder.backend.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserNotFoundExceptionTest {

    private UserNotFoundException userNotFoundException;

    @BeforeEach
    void setUp() {
        userNotFoundException = new UserNotFoundException();
    }

    @Test
    void testGetMessage() {
        String expected = "User not registered yet";
        assertEquals(expected, userNotFoundException.getMessage());
    }

}
