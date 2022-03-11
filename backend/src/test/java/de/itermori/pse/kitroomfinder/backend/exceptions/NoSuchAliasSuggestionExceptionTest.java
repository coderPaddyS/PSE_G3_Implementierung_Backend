package de.itermori.pse.kitroomfinder.backend.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link NoSuchAliasSuggestionException}.
 *
 * @author Adriano Castro
 * @version 1.0
 */
class NoSuchAliasSuggestionExceptionTest {

    private NoSuchAliasSuggestionException noSuchAliasSuggestionException;

    /**
     * Sets up the test resources.
     */
    @BeforeEach
    void setUp() {
        noSuchAliasSuggestionException = new NoSuchAliasSuggestionException();
    }

    /**
     * Tests the method {@link NoSuchAliasSuggestionException#getMessage()}.
     */
    @Test
    void testGetMessage() {
        String expected = "No such alias suggestion exists in the database";
        assertEquals(expected, noSuchAliasSuggestionException.getMessage());
    }

}
