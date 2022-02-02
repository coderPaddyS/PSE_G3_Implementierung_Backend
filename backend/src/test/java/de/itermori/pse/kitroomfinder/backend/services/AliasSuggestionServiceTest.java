package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasSuggestionRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AliasSuggestionServiceTest {

    @Autowired
    private AliasSuggestionService aliasSuggestionService;

    @Autowired
    private AliasSuggestionRepository aliasSuggestionRepository;

    @BeforeEach
    void setUp() {
        aliasSuggestionRepository.deleteAll();
    }

    @Test
    void whenAliasSuggestionNotYetAdded_thenAddAliasSuggestion() {
        // add alias suggestion
        assertTrue(aliasSuggestionService.addAliasSuggestion("HSaF", 1, "user"));

        // check if alias suggestion was added to database
        List<AliasSuggestion> savedAliasSuggestions = aliasSuggestionRepository.findAll();
        assertEquals(1, savedAliasSuggestions.size());
        assertEquals("HSaF", savedAliasSuggestions.get(0).getName());
        assertEquals(1, savedAliasSuggestions.get(0).getMapID());
        assertEquals("user", savedAliasSuggestions.get(0).getSuggester());
    }

    @Test
    void whenAliasSuggestionAddedWithDifferentMapID_thenAddAliasSuggestion() {
        // add alias suggestion once
        assertTrue(aliasSuggestionService.addAliasSuggestion("HSaF", 1, "user"));

        // add same alias suggestion but with different mapID
        assertTrue(aliasSuggestionService.addAliasSuggestion("HSaF", 2, "user"));

        // check if both alias suggestions were added to database
        List<AliasSuggestion> savedAliasSuggestions = aliasSuggestionRepository.findAll();

        assertEquals(2, savedAliasSuggestions.size());

        assertEquals("HSaF", savedAliasSuggestions.get(0).getName());
        assertEquals(1, savedAliasSuggestions.get(0).getMapID());
        assertEquals("user", savedAliasSuggestions.get(0).getSuggester());

        assertEquals("HSaF", savedAliasSuggestions.get(1).getName());
        assertEquals(2, savedAliasSuggestions.get(1).getMapID());
        assertEquals("user", savedAliasSuggestions.get(1).getSuggester());
    }

    @Test
    void whenAliasSuggestionAlreadyAdded_thenAddAliasSuggestion() {
        // add alias suggestion once
        assertTrue(aliasSuggestionService.addAliasSuggestion("HSaF", 1, "user"));

        // add same alias suggestion again
        assertFalse(aliasSuggestionService.addAliasSuggestion("HSaF", 1, "user2"));

        // check if only the first alias suggestion was added to database
        List<AliasSuggestion> savedAliasSuggestions = aliasSuggestionRepository.findAll();
        assertEquals(1, savedAliasSuggestions.size());
        assertEquals("HSaF", savedAliasSuggestions.get(0).getName());
        assertEquals(1, savedAliasSuggestions.get(0).getMapID());
        assertEquals("user", savedAliasSuggestions.get(0).getSuggester());
    }

    @Test
    void whenAliasSuggestionAdded_thenRemoveAliasSuggestion() {
        // add alias suggestion
        assertTrue(aliasSuggestionService.addAliasSuggestion("HSaF", 1, "user"));

        // check if alias suggestion was added to database
        List<AliasSuggestion> savedAliasSuggestions = aliasSuggestionRepository.findAll();
        assertEquals(1, savedAliasSuggestions.size());
        assertEquals("HSaF", savedAliasSuggestions.get(0).getName());
        assertEquals(1, savedAliasSuggestions.get(0).getMapID());
        assertEquals("user", savedAliasSuggestions.get(0).getSuggester());

        // now remove saved alias suggestion
        assertTrue(aliasSuggestionService.removeAliasSuggestion("HSaF", 1));

        // check if alias suggestion was removed from database
        savedAliasSuggestions = aliasSuggestionRepository.findAll();
        assertEquals(0, savedAliasSuggestions.size());
    }

    @Test
    void whenAliasSuggestionsAdded_thenRemoveAliasSuggestion() {
        // add alias suggestions
        assertTrue(aliasSuggestionService.addAliasSuggestion("HSaF", 1, "user"));
        assertTrue(aliasSuggestionService.addAliasSuggestion("HSaF", 2, "user"));

        // check if alias suggestions were added to database
        List<AliasSuggestion> savedAliasSuggestions = aliasSuggestionRepository.findAll();
        assertEquals(2, savedAliasSuggestions.size());
        assertEquals("HSaF", savedAliasSuggestions.get(0).getName());
        assertEquals(1, savedAliasSuggestions.get(0).getMapID());
        assertEquals("user", savedAliasSuggestions.get(0).getSuggester());
        assertEquals("HSaF", savedAliasSuggestions.get(1).getName());
        assertEquals(2, savedAliasSuggestions.get(1).getMapID());
        assertEquals("user", savedAliasSuggestions.get(1).getSuggester());

        // now remove saved alias suggestions
        assertTrue(aliasSuggestionService.removeAliasSuggestion("HSaF"));

        // check if alias suggestions were removed from database
        savedAliasSuggestions = aliasSuggestionRepository.findAll();
        assertEquals(0, savedAliasSuggestions.size());
    }

    @Test
    void whenAliasSuggestionSaved_thenVoteForAlias() {
        // add alias suggestion
        assertTrue(aliasSuggestionService.addAliasSuggestion("HSaF", 1, "user"));

        // check if alias suggestion was added to database
        List<AliasSuggestion> savedAliasSuggestions = aliasSuggestionRepository.findAll();
        assertEquals(1, savedAliasSuggestions.size());
        assertEquals("HSaF", savedAliasSuggestions.get(0).getName());
        assertEquals(1, savedAliasSuggestions.get(0).getMapID());

        // vote for alias suggestion
        assertTrue(aliasSuggestionService.voteForAlias("HSaF", 1, "suggester", true));
        assertTrue(aliasSuggestionService.voteForAlias("HSaF", 1, "suggester2", false));

        // check if amount of votes was updated correctly
        savedAliasSuggestions = aliasSuggestionRepository.findAll();
        assertEquals(1, savedAliasSuggestions.size());
        assertEquals("HSaF", savedAliasSuggestions.get(0).getName());
        assertEquals(1, savedAliasSuggestions.get(0).getMapID());
        assertEquals(1, savedAliasSuggestions.get(0).getPosVotes());
        assertEquals(1, savedAliasSuggestions.get(0).getNegVotes());
    }
}
