package de.itermori.pse.kitroomfinder.backend.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for model {@link MapObject}.
 *
 * @author Adriano Castro
 * @version 1.0
 */
class MapObjectModelTest {

    private MapObject mapObject;

    /**
     * Sets up the test resources.
     */
    @BeforeEach
    void setUp() {
        mapObject = new MapObject("name", 1);
    }

    /**
     * Tests the method {@link MapObject#getName()}.
     */
    @Test
    void testGetName() {
        assertEquals(1, mapObject.getMapID());
    }

    /**
     * Tests the method {@link MapObject#setName(String)}.
     */
    @Test
    void testSetName() {
        mapObject.setName("name2");
        assertEquals("name2", mapObject.getName());
    }

    /**
     * Tests the method {@link MapObject#getMapID()}.
     */
    @Test
    void testGetMapID() {
        assertEquals(1, mapObject.getMapID());
    }

    /**
     * Tests the method {@link MapObject#setMapID(Integer)}.
     */
    @Test
    void testSetMapID() {
        mapObject.setMapID(2);
        assertEquals(2, mapObject.getMapID());
    }

    /**
     * Tests the method {@link MapObject#toString()}.
     */
    @Test
    void testToString() {
        assertEquals("MapObject-" + mapObject.getId(), mapObject.toString());
    }

    /**
     * Cleans up the test resources.
     */
    @AfterEach
    void cleanUp() {
        mapObject = null;
    }

}
