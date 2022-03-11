package de.itermori.pse.kitroomfinder.backend.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for model {@link User}.
 *
 * @author Adriano Castro
 * @version 1.0
 */
class UserModelTest {

    private User user;

    /**
     * Sets up the test resources.
     */
    @BeforeEach
    void setUp() {
        user = new User("name", "USER");
    }

    /**
     * Tests the method {@link User#getName()}.
     */
    @Test
    void testGetName() {
        assertEquals("name", user.getName());
    }

    /**
     * Tests the method {@link User#setName(String)}.
     */
    @Test
    void testSetName() {
        user.setName("name2");
        assertEquals("name2", user.getName());
    }

    /**
     * Tests the method {@link User#getAuthorities()}.
     */
    @Test
    void testGetAuthorities() {
        assertEquals("USER", user.getAuthorities());
    }

    /**
     * Tests the method {@link User#setAuthorities(String)}.
     */
    @Test
    void testSetAuthorities() {
        user.setAuthorities("ADMIN,USER");
        assertEquals("ADMIN,USER", user.getAuthorities());
    }

    /**
     * Tests the method {@link User#toString()}.
     */
    @Test
    void testToString() {
        assertEquals("User-" + user.getId(), user.toString());
    }

    /**
     * Cleans up the test resources.
     */
    @AfterEach
    void cleanUp() {
        user = null;
    }

}
