package de.itermori.pse.kitroomfinder.backend.exceptions;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link BadTokenException}.
 *
 * @author Adriano Castro
 * @version 1.0
 */
class BadTokenExceptionTest {

    private BadTokenException badTokenException;

    /**
     * Sets up the test resources.
     */
    @BeforeEach
    void setUp() {
        badTokenException = new BadTokenException();
    }

    /**
     * Tests the method {@link BadTokenException#getMessage()}.
     */
    @Test
    void testGetMessage() {
        String expected = "invalid Token";
        assertEquals(expected, badTokenException.getMessage());
    }

}
