package de.itermori.pse.kitroomfinder.backend.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoSuchAliasSuggestionExceptionTest {

    private NoSuchAliasSuggestionException noSuchAliasSuggestionException;

    @BeforeEach
    void setUp() {
        noSuchAliasSuggestionException = new NoSuchAliasSuggestionException();
    }

    @Test
    void testGetMessage() {
        String expected = "No such alias suggestion exists in the database";
        assertEquals(expected, noSuchAliasSuggestionException.getMessage());
    }

}
