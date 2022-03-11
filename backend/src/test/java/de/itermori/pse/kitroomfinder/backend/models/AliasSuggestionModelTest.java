package de.itermori.pse.kitroomfinder.backend.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AliasSuggestionModelTest {

    private AliasSuggestion aliasSuggestion;

    @BeforeEach
    void setUp() {
        aliasSuggestion = new AliasSuggestion("name", 1, "mapObject", "suggester");
    }

    @Test
    void testGetName() {
        assertEquals("name", aliasSuggestion.getName());
    }

    @Test
    void testSetName() {
        aliasSuggestion.setName("aliasSuggestion");
        assertEquals("aliasSuggestion", aliasSuggestion.getName());
    }

    @Test
    void testGetMapID() {
        assertEquals(1, aliasSuggestion.getMapID());
    }

    @Test
    void testSetMapID() {
        aliasSuggestion.setMapID(2);
        assertEquals(2, aliasSuggestion.getMapID());
    }

    @Test
    void testGetMapObject() {
        assertEquals("mapObject", aliasSuggestion.getMapObject());
    }

    @Test
    void testSetMapObject() {
        aliasSuggestion.setMapObject("mapObject2");
        assertEquals("mapObject2", aliasSuggestion.getMapObject());
    }

    @Test
    void testGetSuggester() {
        assertEquals("suggester", aliasSuggestion.getSuggester());
    }

    @Test
    void testSetSuggester() {
        aliasSuggestion.setSuggester("suggester2");
        assertEquals("suggester2", aliasSuggestion.getSuggester());
    }

    @Test
    void testGetVoters() {
        assertEquals("suggester", aliasSuggestion.getVoters());
    }

    @Test
    void testSetVoters() {
        aliasSuggestion.setVoters("suggester,voter");
        assertEquals("suggester,voter", aliasSuggestion.getVoters());
    }

    @Test
    void testGetPosVotes() {
        assertEquals(0, aliasSuggestion.getPosVotes());
    }

    @Test
    void testSetPosVotes() {
        aliasSuggestion.setPosVotes(1);
        assertEquals(1, aliasSuggestion.getPosVotes());
    }

    @Test
    void testGetNegVotes() {
        assertEquals(0, aliasSuggestion.getNegVotes());
    }

    @Test
    void testSetNegVotes() {
        aliasSuggestion.setNegVotes(1);
        assertEquals(1, aliasSuggestion.getNegVotes());
    }

    @Test
    void testToString() {
        assertEquals("AliasSuggestion-" + aliasSuggestion.getId(), aliasSuggestion.toString());
    }

    @AfterEach
    void cleanUp() {
        aliasSuggestion = null;
    }

}
