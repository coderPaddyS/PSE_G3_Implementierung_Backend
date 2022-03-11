package de.itermori.pse.kitroomfinder.backend.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link BlacklistEntry}.
 *
 * @author Adriano Castro
 * @version 1.0
 */
class BlacklistEntryModelTest {

    private BlacklistEntry blacklistEntry;

    /**
     * Sets up the test resources.
     */
    @BeforeEach
    void setUp() {
        blacklistEntry = new BlacklistEntry("forbidden");
    }

    /**
     * Tests the method {@link BlacklistEntry#getName()}.
     */
    @Test
    void testGetName() {
        assertEquals("forbidden", blacklistEntry.getName());
    }

    /**
     * Tests the method {@link BlacklistEntry#setName(String)}.
     */
    @Test
    void testSetName() {
        blacklistEntry.setName("forbidden2");
        assertEquals("forbidden2", blacklistEntry.getName());
    }

    /**
     * Tests the method {@link BlacklistEntry#toString()}.
     */
    @Test
    void testToString() {
        assertEquals("BlacklistEntry-" + blacklistEntry.getId(), blacklistEntry.toString());
    }

    /**
     * Cleans up the test resources.
     */
    @AfterEach
    void cleanUp() {
        blacklistEntry = null;
    }

}
