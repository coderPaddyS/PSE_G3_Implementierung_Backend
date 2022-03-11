package de.itermori.pse.kitroomfinder.backend.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BadTokenExceptionTest {

    private BadTokenException badTokenException;

    @BeforeEach
    void setUp() {
        badTokenException = new BadTokenException();
    }

    @Test
    void testGetMessage() {
        String expected = "invalid Token";
        assertEquals(expected, badTokenException.getMessage());
    }

}
