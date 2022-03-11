package de.itermori.pse.kitroomfinder.backend.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for model {@link Alias}.
 *
 * @author Adriano Castro
 * @version 1.0
 */
class AliasModelTest {

    private Alias alias;

    /**
     * Sets up the test resources.
     */
    @BeforeEach
    void setUp() {
        alias = new Alias("name", 1, "mapObject", 1);
    }

    /**
     * Tests the method {@link Alias#getName()}.
     */
    @Test
    void testGetName() {
        assertEquals("name", alias.getName());
    }

    /**
     * Tests the method {@link Alias#setName(String)}.
     */
    @Test
    void testSetName() {
        alias.setName("alias");
        assertEquals("alias", alias.getName());
    }

    /**
     * Tests the method {@link Alias#getVersion()}.
     */
    @Test
    void testGetVersion() {
        assertEquals(1, alias.getVersion());
    }

    /**
     * Tests the method {@link Alias#setVersion(int)}.
     */
    @Test
    void testSetVersion() {
        alias.setVersion(2);
        assertEquals(2, alias.getVersion());
    }

    /**
     * Tests the method {@link Alias#getMapID()}.
     */
    @Test
    void testGetMapID() {
        assertEquals(1, alias.getMapID());
    }

    /**
     * Tests the method {@link Alias#setMapID(Integer)}.
     */
    @Test
    void testSetMapID() {
        alias.setMapID(2);
        assertEquals(2, alias.getMapID());
    }

    /**
     * Tests the method {@link Alias#getMapObject()}.
     */
    @Test
    void testGetMapObject() {
        assertEquals("mapObject", alias.getMapObject());
    }

    /**
     * Tests the method {@link Alias#setMapObject(String)}.
     */
    @Test
    void testSetMapObject() {
        alias.setMapObject("mapObject2");
        assertEquals("mapObject2", alias.getMapObject());
    }

    /**
     * Tests the method {@link Alias#toString()}.
     */
    @Test
    void testToString() {
        assertEquals("Alias-" + alias.getId(), alias.toString());
    }

    /**
     * Cleans up the test resources.
     */
    @AfterEach
    void cleanUp() {
        alias = null;
    }

}
