package de.itermori.pse.kitroomfinder.backend.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapObjectModelTest {

    private MapObject mapObject;

    @BeforeEach
    void setUp() {
        mapObject = new MapObject("name", 1);
    }

    @Test
    void testGetName() {
        assertEquals(1, mapObject.getMapID());
    }

    @Test
    void testSetName() {
        mapObject.setName("name2");
        assertEquals("name2", mapObject.getName());
    }

    @Test
    void testGetMapID() {
        assertEquals(1, mapObject.getMapID());
    }

    @Test
    void testSetMapID() {
        mapObject.setMapID(2);
        assertEquals(2, mapObject.getMapID());
    }

    @Test
    void testToString() {
        assertEquals("MapObject-" + mapObject.getId(), mapObject.toString());
    }

    @AfterEach
    void cleanUp() {
        mapObject = null;
    }

}
