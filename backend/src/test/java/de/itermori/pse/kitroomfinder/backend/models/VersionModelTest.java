package de.itermori.pse.kitroomfinder.backend.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VersionModelTest {

    private Version version;

    @BeforeEach
    void setUp() {
        version = new Version(1);
    }

    @Test
    void testGetCurrentVersion() {
        assertEquals(1, version.getCurrentVersion());
    }

    @Test
    void testSetCurrentVersion() {
        version.setCurrentVersion(2);
        assertEquals(2, version.getCurrentVersion());
    }

    @Test
    void testToString() {
        assertEquals("Version-" + version.getId(), version.toString());
    }

    @AfterEach
    void cleanUp() {
        version = null;
    }

}
