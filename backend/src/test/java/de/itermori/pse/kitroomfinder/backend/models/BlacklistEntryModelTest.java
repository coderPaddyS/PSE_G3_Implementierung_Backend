package de.itermori.pse.kitroomfinder.backend.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlacklistEntryModelTest {

    private BlacklistEntry blacklistEntry;

    @BeforeEach
    void setUp() {
        blacklistEntry = new BlacklistEntry("forbidden");
    }

    @Test
    void testGetName() {
        assertEquals("forbidden", blacklistEntry.getName());
    }

    @Test
    void testSetName() {
        blacklistEntry.setName("forbidden2");
        assertEquals("forbidden2", blacklistEntry.getName());
    }

    @Test
    void testToString() {
        assertEquals("BlacklistEntry-" + blacklistEntry.getId(), blacklistEntry.toString());
    }

    @AfterEach
    void cleanUp() {
        blacklistEntry = null;
    }

}
