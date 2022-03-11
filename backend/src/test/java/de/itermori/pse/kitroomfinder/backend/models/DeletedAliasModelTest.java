package de.itermori.pse.kitroomfinder.backend.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for model {@link DeletedAlias}.
 *
 * @author Adriano Castro
 * @version 1.0
 */
class DeletedAliasModelTest {

    private DeletedAlias deletedAlias;

    /**
     * Sets up the test resources.
     */
    @BeforeEach
    void setUp() {
        deletedAlias = new DeletedAlias("name", 1, 1);
    }

    /**
     * Tests the method {@link DeletedAlias#getName()}.
     */
    @Test
    void testGetName() {
        assertEquals("name", deletedAlias.getName());
    }

    /**
     * Tests the method {@link DeletedAlias#setName(String)}.
     */
    @Test
    void testSetName() {
        deletedAlias.setName("name2");
        assertEquals("name2", deletedAlias.getName());
    }

    /**
     * Tests the method {@link DeletedAlias#getVersion()}.
     */
    @Test
    void testGetVersion() {
        assertEquals(1, deletedAlias.getVersion());
    }

    /**
     * Tests the method {@link DeletedAlias#setVersion(int)}.
     */
    @Test
    void testSetVersion() {
        deletedAlias.setVersion(2);
        assertEquals(2, deletedAlias.getVersion());
    }

    /**
     * Tests the method {@link DeletedAlias#getMapID()}.
     */
    @Test
    void testGetMapID() {
        assertEquals(1, deletedAlias.getMapID());
    }

    /**
     * Tests the method {@link DeletedAlias#setMapID(Integer)}.
     */
    @Test
    void testSetMapID() {
        deletedAlias.setMapID(2);
        assertEquals(2, deletedAlias.getMapID());
    }

    /**
     * Tests the method {@link DeletedAlias#toString()}.
     */
    @Test
    void testToString() {
        assertEquals("DeletedAlias-" + deletedAlias.getId(), deletedAlias.toString());
    }

    /**
     * Cleans up the test resources.
     */
    @AfterEach
    void cleanUp() {
        deletedAlias = null;
    }

}
