package de.itermori.pse.kitroomfinder.backend.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserModelTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("name", "USER");
    }

    @Test
    void testGetName() {
        assertEquals("name", user.getName());
    }

    @Test
    void testSetName() {
        user.setName("name2");
        assertEquals("name2", user.getName());
    }

    @Test
    void testGetAuthorities() {
        assertEquals("USER", user.getAuthorities());
    }

    @Test
    void testSetAuthorities() {
        user.setAuthorities("ADMIN,USER");
        assertEquals("ADMIN,USER", user.getAuthorities());
    }

    @Test
    void testToString() {
        assertEquals("User-" + user.getId(), user.toString());
    }

    @AfterEach
    void cleanUp() {
        user = null;
    }

}
