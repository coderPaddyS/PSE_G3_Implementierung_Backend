package de.itermori.pse.kitroomfinder.backend.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for model {@link Version}.
 *
 * @author Adriano Castro
 * @version 1.0
 */
class VersionModelTest {

    private Version version;

    /**
     * Sets up the test resources.
     */
    @BeforeEach
    void setUp() {
        version = new Version(1);
    }

    /**
     * Tests the method {@link Version#getCurrentVersion()}.
     */
    @Test
    void testGetCurrentVersion() {
        assertEquals(1, version.getCurrentVersion());
    }

    /**
     * Tests the method {@link Version#setCurrentVersion(Integer)}.
     */
    @Test
    void testSetCurrentVersion() {
        version.setCurrentVersion(2);
        assertEquals(2, version.getCurrentVersion());
    }

    /**
     * Tests the method {@link Version#toString()}.
     */
    @Test
    void testToString() {
        assertEquals("Version-" + version.getId(), version.toString());
    }

    /**
     * Cleans up the test resources.
     */
    @AfterEach
    void cleanUp() {
        version = null;
    }

}
