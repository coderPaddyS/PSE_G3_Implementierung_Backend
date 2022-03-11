package de.itermori.pse.kitroomfinder.backend.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for model {@link AliasSuggestion}.
 *
 * @author Adriano Castro
 * @version 1.0
 */
class AliasSuggestionModelTest {

    private AliasSuggestion aliasSuggestion;

    /**
     * Sets up the test resources.
     */
    @BeforeEach
    void setUp() {
        aliasSuggestion = new AliasSuggestion("name", 1, "mapObject", "suggester");
    }

    /**
     * Tests the method {@link AliasSuggestion#getName()}.
     */
    @Test
    void testGetName() {
        assertEquals("name", aliasSuggestion.getName());
    }

    /**
     * Tests the method {@link AliasSuggestion#setName(String)}.
     */
    @Test
    void testSetName() {
        aliasSuggestion.setName("aliasSuggestion");
        assertEquals("aliasSuggestion", aliasSuggestion.getName());
    }

    /**
     * Tests the method {@link AliasSuggestion#getMapID()}.
     */
    @Test
    void testGetMapID() {
        assertEquals(1, aliasSuggestion.getMapID());
    }

    /**
     * Tests the method {@link AliasSuggestion#setMapID(Integer)}.
     */
    @Test
    void testSetMapID() {
        aliasSuggestion.setMapID(2);
        assertEquals(2, aliasSuggestion.getMapID());
    }

    /**
     * Tests the method {@link AliasSuggestion#getMapObject()}.
     */
    @Test
    void testGetMapObject() {
        assertEquals("mapObject", aliasSuggestion.getMapObject());
    }

    /**
     * Tests the method {@link AliasSuggestion#setMapObject(String)} .
     */
    @Test
    void testSetMapObject() {
        aliasSuggestion.setMapObject("mapObject2");
        assertEquals("mapObject2", aliasSuggestion.getMapObject());
    }

    /**
     * Tests the method {@link AliasSuggestion#getSuggester()}.
     */
    @Test
    void testGetSuggester() {
        assertEquals("suggester", aliasSuggestion.getSuggester());
    }

    /**
     * Tests the method {@link AliasSuggestion#setSuggester(String)}.
     */
    @Test
    void testSetSuggester() {
        aliasSuggestion.setSuggester("suggester2");
        assertEquals("suggester2", aliasSuggestion.getSuggester());
    }

    /**
     * Tests the method {@link AliasSuggestion#setVoters(String)}.
     */
    @Test
    void testGetVoters() {
        assertEquals("suggester", aliasSuggestion.getVoters());
    }

    /**
     * Tests the method {@link AliasSuggestion#setVoters(String)}.
     */
    @Test
    void testSetVoters() {
        aliasSuggestion.setVoters("suggester,voter");
        assertEquals("suggester,voter", aliasSuggestion.getVoters());
    }

    /**
     * Tests the method {@link AliasSuggestion#getPosVotes()}.
     */
    @Test
    void testGetPosVotes() {
        assertEquals(0, aliasSuggestion.getPosVotes());
    }

    /**
     * Tests the method {@link AliasSuggestion#setPosVotes(Integer)}.
     */
    @Test
    void testSetPosVotes() {
        aliasSuggestion.setPosVotes(1);
        assertEquals(1, aliasSuggestion.getPosVotes());
    }

    /**
     * Tests the method {@link AliasSuggestion#getNegVotes()}.
     */
    @Test
    void testGetNegVotes() {
        assertEquals(0, aliasSuggestion.getNegVotes());
    }

    /**
     * Tests the method {@link AliasSuggestion#setNegVotes(Integer)}.
     */
    @Test
    void testSetNegVotes() {
        aliasSuggestion.setNegVotes(1);
        assertEquals(1, aliasSuggestion.getNegVotes());
    }

    /**
     * Tests the method {@link AliasSuggestion#toString()}.
     */
    @Test
    void testToString() {
        assertEquals("AliasSuggestion-" + aliasSuggestion.getId(), aliasSuggestion.toString());
    }

    /**
     * Cleans up the test resources.
     */
    @AfterEach
    void cleanUp() {
        aliasSuggestion = null;
    }

}
