package de.itermori.pse.kitroomfinder.backend.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeletedAliasModelTest {

    private DeletedAlias deletedAlias;

    @BeforeEach
    void setUp() {
        deletedAlias = new DeletedAlias("name", 1, 1);
    }

    @Test
    void testGetName() {
        assertEquals("name", deletedAlias.getName());
    }

    @Test
    void testSetName() {
        deletedAlias.setName("name2");
        assertEquals("name2", deletedAlias.getName());
    }

    @Test
    void testGetVersion() {
        assertEquals(1, deletedAlias.getVersion());
    }

    @Test
    void testSetVersion() {
        deletedAlias.setVersion(2);
        assertEquals(2, deletedAlias.getVersion());
    }

    @Test
    void testGetMapID() {
        assertEquals(1, deletedAlias.getMapID());
    }

    @Test
    void testSetMapID() {
        deletedAlias.setMapID(2);
        assertEquals(2, deletedAlias.getMapID());
    }

    @Test
    void testToString() {
        assertEquals("DeletedAlias-" + deletedAlias.getId(), deletedAlias.toString());
    }

    @AfterEach
    void cleanUp() {
        deletedAlias = null;
    }

}
