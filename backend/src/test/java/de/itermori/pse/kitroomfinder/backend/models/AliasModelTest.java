package de.itermori.pse.kitroomfinder.backend.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AliasModelTest {

    private Alias alias;

    @BeforeEach
    void setUp() {
        alias = new Alias("name", 1, "mapObject", 1);
    }

    @Test
    void testGetName() {
        assertEquals("name", alias.getName());
    }

    @Test
    void testSetName() {
        alias.setName("alias");
        assertEquals("alias", alias.getName());
    }

    @Test
    void testGetVersion() {
        assertEquals(1, alias.getVersion());
    }

    @Test
    void testSetVersion() {
        alias.setVersion(2);
        assertEquals(2, alias.getVersion());
    }

    @Test
    void testGetMapID() {
        assertEquals(1, alias.getMapID());
    }

    @Test
    void testSetMapID() {
        alias.setMapID(2);
        assertEquals(2, alias.getMapID());
    }

    @Test
    void testGetMapObject() {
        assertEquals("mapObject", alias.getMapObject());
    }

    @Test
    void testSetMapObject() {
        alias.setMapObject("mapObject2");
        assertEquals("mapObject2", alias.getMapObject());
    }

    @Test
    void testToString() {
        assertEquals("Alias-" + alias.getId(), alias.toString());
    }

    @AfterEach
    void cleanUp() {
        alias = null;
    }

}
