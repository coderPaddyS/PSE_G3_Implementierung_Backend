package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import de.itermori.pse.kitroomfinder.backend.models.BlacklistEntry;
import de.itermori.pse.kitroomfinder.backend.models.MapObject;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasSuggestionRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.BlacklistRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.MapObjectRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link AliasSuggestionService}.
 *
 * @author Adriano Castro
 * @version 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AliasSuggestionServiceTest {

    @Autowired
    private AliasSuggestionService aliasSuggestionService;

    @Autowired
    private AliasSuggestionRepository aliasSuggestionRepository;

    @Autowired
    private MapObjectRepository mapObjectRepository;

    @Autowired
    private BlacklistRepository blacklistRepository;


    /**
     * Sets up the test resources.
     */
    @BeforeEach
    void setUp() {
        aliasSuggestionRepository.deleteAll();
        mapObjectRepository.deleteAll();
    }

    /**
     * Tests the method {@link AliasSuggestionService#addAliasSuggestion(String, int, String)}.
     */
    @Test
    void whenAliasSuggestionNotYetAdded_thenAddAliasSuggestion() {
        // add map object first
        mapObjectRepository.save(new MapObject("50.34", 1));

        // add alias suggestion
        assertTrue(aliasSuggestionService.addAliasSuggestion("HSaF", 1, "user"));

        // check if alias suggestion was added to database
        List<AliasSuggestion> savedAliasSuggestions = aliasSuggestionRepository.findAll();
        assertEquals(1, savedAliasSuggestions.size());
        assertEquals("HSaF", savedAliasSuggestions.get(0).getName());
        assertEquals(1, savedAliasSuggestions.get(0).getMapID());
        assertEquals("user", savedAliasSuggestions.get(0).getSuggester());
    }

    /**
     * Tests the method {@link AliasSuggestionService#addAliasSuggestion(String, int, String)}.
     */
    @Test
    void whenAliasSuggestionAddedWithDifferentMapID_thenAddAliasSuggestion() {
        // add map objects first
        mapObjectRepository.save(new MapObject("50.34", 1));
        mapObjectRepository.save(new MapObject("50.35", 2));
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

    /**
     * Tests the method {@link AliasSuggestionService#addAliasSuggestion(String, int, String)}.
     */
    @Test
    void whenAliasSuggestionAlreadyAdded_thenAddAliasSuggestion() {
        // add map object first
        mapObjectRepository.save(new MapObject("50.34", 1));

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

    /**
     * Tests the method {@link AliasSuggestionService#removeAliasSuggestion(String, int)}.
     */
    @Test
    void whenAliasSuggestionAdded_thenRemoveAliasSuggestion() {
        // save alias suggestion in database
        AliasSuggestion aliasSuggestionToRemove = new AliasSuggestion("HSaF", 1, "50.34", "suggester");
        aliasSuggestionRepository.save(aliasSuggestionToRemove);

        // now remove saved alias suggestion
        assertTrue(aliasSuggestionService.removeAliasSuggestion("HSaF", 1));

        // check if alias suggestion was removed from database
        List<AliasSuggestion> savedAliasSuggestions = aliasSuggestionRepository.findAll();
        assertEquals(0, savedAliasSuggestions.size());
    }

    /**
     * Tests the method {@link AliasSuggestionService#removeAliasSuggestion(String)}.
     */
    @Test
    void whenAliasSuggestionsAdded_thenRemoveAliasSuggestion() {
        // save alias suggestion in database
        AliasSuggestion aliasSuggestionToRemove1 = new AliasSuggestion("HSaF", 1, "50.34", "suggester");
        AliasSuggestion aliasSuggestionToRemove2 = new AliasSuggestion("HSaF", 2, "50.34", "suggester");
        aliasSuggestionRepository.save(aliasSuggestionToRemove1);
        aliasSuggestionRepository.save(aliasSuggestionToRemove2);

        // now remove saved alias suggestions
        assertTrue(aliasSuggestionService.removeAliasSuggestion("HSaF"));

        // check if alias suggestions were removed from database
        List<AliasSuggestion> savedAliasSuggestions = aliasSuggestionRepository.findAll();
        assertEquals(0, savedAliasSuggestions.size());
    }

    /**
     * Tests the method {@link AliasSuggestionService#voteForAlias(String, int, String, boolean)}.
     */
    @Test
    void whenAliasSuggestionSaved_thenVoteForAlias() {
        // save alias suggestion in database
        AliasSuggestion aliasSuggestionToVoteFor = new AliasSuggestion("HSaF", 1, "50.34", "suggester");
        aliasSuggestionRepository.save(aliasSuggestionToVoteFor);

        // vote for alias suggestion
        assertTrue(aliasSuggestionService.voteForAlias("HSaF", 1, "user", true));
        assertTrue(aliasSuggestionService.voteForAlias("HSaF", 1, "user2", false));

        // check if amount of votes was updated correctly
        List<AliasSuggestion> savedAliasSuggestions = aliasSuggestionRepository.findAll();
        assertEquals(1, savedAliasSuggestions.size());
        assertEquals(aliasSuggestionToVoteFor, savedAliasSuggestions.get(0));
        assertEquals(1, savedAliasSuggestions.get(0).getPosVotes());
        assertEquals(1, savedAliasSuggestions.get(0).getNegVotes());
    }

    /**
     * Tests the method {@link AliasSuggestionService#voteForAlias(String, int, String, boolean)}.
     */
    @Test
    void whenAlreadyVotedForAlias_thenVoteForAlias() {
        // save alias suggestion in database
        AliasSuggestion aliasSuggestionToVoteFor = new AliasSuggestion("HSaF", 1, "50.34", "suggester");
        aliasSuggestionRepository.save(aliasSuggestionToVoteFor);

        // vote for alias suggestion
        assertTrue(aliasSuggestionService.voteForAlias("HSaF", 1, "user", true));
        assertFalse(aliasSuggestionService.voteForAlias("HSaF", 1, "user", true));
        assertFalse(aliasSuggestionService.voteForAlias("HSaF", 1, "user", false));

        // check if amount of votes was updated correctly
        List<AliasSuggestion> savedAliasSuggestions = aliasSuggestionRepository.findAll();
        assertEquals(1, savedAliasSuggestions.size());
        assertEquals(aliasSuggestionToVoteFor, savedAliasSuggestions.get(0));
        assertEquals(1, savedAliasSuggestions.get(0).getPosVotes());
        assertEquals(0, savedAliasSuggestions.get(0).getNegVotes());
    }

    /**
     * Tests the method {@link AliasSuggestionService#voteForAlias(String, int, String, boolean)}.
     */
    @Test
    void whenAliasSuggestionSaved_thenSuggesterVoteForAlias() {
        // save alias suggestion in database
        AliasSuggestion aliasSuggestionToVoteFor = new AliasSuggestion("HSaF", 1, "50.34", "suggester");
        aliasSuggestionRepository.save(aliasSuggestionToVoteFor);

        // vote for alias suggestion
        assertFalse(aliasSuggestionService.voteForAlias("HSaF", 1, "suggester", true));
        assertFalse(aliasSuggestionService.voteForAlias("HSaF", 1, "suggester", false));

        // check if amount of votes remained unchanged
        List<AliasSuggestion> savedAliasSuggestions = aliasSuggestionRepository.findAll();
        assertEquals(1, savedAliasSuggestions.size());
        assertEquals(aliasSuggestionToVoteFor, savedAliasSuggestions.get(0));
        assertEquals(0, savedAliasSuggestions.get(0).getPosVotes());
        assertEquals(0, savedAliasSuggestions.get(0).getNegVotes());
    }

    /**
     * Tests the method {@link AliasSuggestionService#getAliasSuggestions(int, int)}.
     */
    @Test
    void whenAliasSuggestionsSaved_thenGetAliasSuggestions() {
        // save alias suggestions in database
        AliasSuggestion aliasSuggestion1 = new AliasSuggestion("HSaF", 1, "50.34", "user");
        AliasSuggestion aliasSuggestion2 = new AliasSuggestion("HSaF", 2, "50.34", "user");
        aliasSuggestionRepository.save(aliasSuggestion1);
        aliasSuggestionRepository.save(aliasSuggestion2);

        // vote for alias suggestion
        assertTrue(aliasSuggestionService.voteForAlias("HSaF", 1, "voter1", true));
        assertTrue(aliasSuggestionService.voteForAlias("HSaF", 1, "voter2", true));
        assertTrue(aliasSuggestionService.voteForAlias("HSaF", 1, "voter3", false));
        assertTrue(aliasSuggestionService.voteForAlias("HSaF", 2, "voter1", true));

        // get alias suggestions and save them in a list for simpler testing
        Iterable<AliasSuggestion> aliasSuggestionIterable = aliasSuggestionService.getAliasSuggestions(1, 1);
        Iterator<AliasSuggestion> iterator = aliasSuggestionIterable.iterator();
        List<AliasSuggestion> aliasSuggestions = new ArrayList<>();
        while (iterator.hasNext()) {
            aliasSuggestions.add(iterator.next());
        }

        assertEquals(1, aliasSuggestions.size());
        assertEquals(aliasSuggestion1, aliasSuggestions.get(0));
    }

    /**
     * Tests the method {@link AliasSuggestionService#getAliasSuggestionsAmount(int, int, String)}.
     */
    @Test
    void whenAliasSuggestionsSaved_thenGetAliasSuggestionsAmount() {
        mapObjectRepository.save(new MapObject("50.34", 1));
        // save alias suggestions in database
        AliasSuggestion aliasSuggestion1 = new AliasSuggestion("HSaF", 1, "50.34", "b");
        AliasSuggestion aliasSuggestion2 = new AliasSuggestion("Infobau", 1, "50.34", "c");
        AliasSuggestion aliasSuggestion3 = new AliasSuggestion("Info", 1, "50.34", "a");
        aliasSuggestionRepository.save(aliasSuggestion1);
        aliasSuggestionRepository.save(aliasSuggestion2);
        aliasSuggestionRepository.save(aliasSuggestion3);

        // get alias suggestions and save them in a list for simpler testing
        Iterable<AliasSuggestion> aliasSuggestionIterable
                = aliasSuggestionService.getAliasSuggestionsAmount(1, 2, "user");
        Iterator<AliasSuggestion> iterator = aliasSuggestionIterable.iterator();
        List<AliasSuggestion> aliasSuggestions = new ArrayList<>();
        while (iterator.hasNext()) {
            aliasSuggestions.add(iterator.next());
        }

        assertEquals(2, aliasSuggestions.size());
        assertEquals(aliasSuggestion1, aliasSuggestions.get(0));
        assertEquals(aliasSuggestion2, aliasSuggestions.get(1));
    }

    /**
     * Tests the method {@link AliasSuggestionService#addAliasSuggestion(String, int, String)}.
     */
    @Test
    void whenWordBlacklisted_thenAddAliasSuggestion() {
        BlacklistEntry blacklistEntry = new BlacklistEntry("forbidden");
        blacklistRepository.save(blacklistEntry);

        assertFalse(aliasSuggestionService.addAliasSuggestion("forbidden", 1, "suggester"));

        // check if alias suggestion was not added to database
        List<AliasSuggestion> aliasSuggestions = aliasSuggestionRepository.findAll();
        assertTrue(aliasSuggestions.isEmpty());
    }

}
