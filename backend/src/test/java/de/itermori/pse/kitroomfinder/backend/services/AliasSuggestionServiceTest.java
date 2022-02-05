package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import de.itermori.pse.kitroomfinder.backend.models.BlacklistEntry;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasSuggestionRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.BlacklistRepository;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AliasSuggestionServiceTest {

    @Autowired
    private AliasSuggestionService aliasSuggestionService;

    @Autowired
    private AliasSuggestionRepository aliasSuggestionRepository;

    @Autowired
    private BlacklistRepository blacklistRepository;

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
        // save alias suggestion in database
        AliasSuggestion aliasSuggestionToRemove = new AliasSuggestion("HSaF", 1, "suggester");
        aliasSuggestionRepository.save(aliasSuggestionToRemove);

        // now remove saved alias suggestion
        assertTrue(aliasSuggestionService.removeAliasSuggestion("HSaF", 1));

        // check if alias suggestion was removed from database
        List<AliasSuggestion> savedAliasSuggestions = aliasSuggestionRepository.findAll();
        assertEquals(0, savedAliasSuggestions.size());
    }

    @Test
    void whenAliasSuggestionsAdded_thenRemoveAliasSuggestion() {
        // save alias suggestion in database
        AliasSuggestion aliasSuggestionToRemove1 = new AliasSuggestion("HSaF", 1, "suggester");
        AliasSuggestion aliasSuggestionToRemove2 = new AliasSuggestion("HSaF", 2, "suggester");
        aliasSuggestionRepository.save(aliasSuggestionToRemove1);
        aliasSuggestionRepository.save(aliasSuggestionToRemove2);

        // now remove saved alias suggestions
        assertTrue(aliasSuggestionService.removeAliasSuggestion("HSaF"));

        // check if alias suggestions were removed from database
        List<AliasSuggestion> savedAliasSuggestions = aliasSuggestionRepository.findAll();
        assertEquals(0, savedAliasSuggestions.size());
    }

    @Test
    void whenAliasSuggestionSaved_thenVoteForAlias() {
        // save alias suggestion in database
        AliasSuggestion aliasSuggestionToVoteFor = new AliasSuggestion("HSaF", 1, "suggester");
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

    @Test
    void whenAlreadyVotedForAlias_thenVoteForAlias() {
        // save alias suggestion in database
        AliasSuggestion aliasSuggestionToVoteFor = new AliasSuggestion("HSaF", 1, "suggester");
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

    @Test
    void whenAliasSuggestionSaved_thenSuggesterVoteForAlias() {
        // save alias suggestion in database
        AliasSuggestion aliasSuggestionToVoteFor = new AliasSuggestion("HSaF", 1, "suggester");
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

    @Test
    void whenAliasSuggestionsSaved_thenGetAliasSuggestions() {
        // save alias suggestions in database
        AliasSuggestion aliasSuggestion1 = new AliasSuggestion("HSaF", 1, "user");
        AliasSuggestion aliasSuggestion2 = new AliasSuggestion("HSaF", 2, "user");
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

    @Test
    void whenAliasSuggestionsSaved_thenGetAliasSuggestionsAmount() {
        // save alias suggestions in database
        AliasSuggestion aliasSuggestion1 = new AliasSuggestion("HSaF", 1, "user");
        AliasSuggestion aliasSuggestion2 = new AliasSuggestion("Infobau", 1, "user");
        AliasSuggestion aliasSuggestion3 = new AliasSuggestion("Info", 1, "user");
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
