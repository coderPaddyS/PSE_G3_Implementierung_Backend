package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AliasSuggestionRepositoryTest {

    @Autowired
    private AliasSuggestionRepository aliasSuggestionRepository;

    @BeforeEach
    void setUp() {
        aliasSuggestionRepository.deleteAll();
    }

    @Test
    void whenAliasSuggestionSaved_findByNameAndMapID() {
        AliasSuggestion expectedAliasSuggestion = new AliasSuggestion("HSaF", 1, "Suggester");
        aliasSuggestionRepository.save(expectedAliasSuggestion);
        AliasSuggestion actualAliasSuggestion = aliasSuggestionRepository.findByNameAndMapID("HSaF", 1);
        assertEquals(expectedAliasSuggestion, actualAliasSuggestion);
    }

    @Test
    void whenAliasSuggestionSaved_deleteByNameAndID() {
        // first save alias suggestion in database
        AliasSuggestion toDelete = new AliasSuggestion("HSaF", 1, "Suggester");
        aliasSuggestionRepository.save(toDelete);

        // now check if it is saved in database
        Iterable<AliasSuggestion> actualAliasSuggestions = aliasSuggestionRepository.findAll();
        Iterator<AliasSuggestion> aliasIterator = actualAliasSuggestions.iterator();
        int actualAmountAliases = 0;
        AliasSuggestion actualAliasSuggestion = null;
        while (aliasIterator.hasNext()) {
            ++actualAmountAliases;
            actualAliasSuggestion = aliasIterator.next(); // now correct value should be saved in actualAliasSuggestion
        }
        assertEquals(1, actualAmountAliases);
        assertEquals(toDelete, actualAliasSuggestion);

        // now delete it
        aliasSuggestionRepository.deleteByNameAndID(1, toDelete.getName());

        List<AliasSuggestion> aliasSuggestionsSaved = aliasSuggestionRepository.findAll();
        assertTrue(aliasSuggestionsSaved.isEmpty());
    }

    @Test
    void whenAliasSuggestionSaved_votePos() {
        // first save alias suggestion in database
        AliasSuggestion toVoteFor = new AliasSuggestion("HSaF", 1, "Suggester");
        aliasSuggestionRepository.save(toVoteFor);

        // now check if it is saved in database
        Iterable<AliasSuggestion> actualAliasSuggestions = aliasSuggestionRepository.findAll();
        Iterator<AliasSuggestion> aliasIterator = actualAliasSuggestions.iterator();
        int actualAmountAliases = 0;
        AliasSuggestion actualAliasSuggestion = null;
        while (aliasIterator.hasNext()) {
            ++actualAmountAliases;
            actualAliasSuggestion = aliasIterator.next(); // now correct value should be saved in actualAliasSuggestion
        }
        assertEquals(1, actualAmountAliases);
        assertEquals(toVoteFor, actualAliasSuggestion);

        // now vote for alias suggestion
        aliasSuggestionRepository.votePos(toVoteFor.getMapID(), toVoteFor.getName());

        // get the updated aliasSuggestion from the database
        actualAliasSuggestions = aliasSuggestionRepository.findAll();
        aliasIterator = actualAliasSuggestions.iterator();
        actualAliasSuggestion = null;
        while (aliasIterator.hasNext()) {
            actualAliasSuggestion = aliasIterator.next(); // now correct value should be saved in actualAliasSuggestion
        }

        assertEquals(1, actualAliasSuggestion.getPosVotes());
    }

    @Test
    void whenAliasSuggestionSaved_voteNeg() {
        // first save alias suggestion in database
        AliasSuggestion toVoteFor = new AliasSuggestion("HSaF", 1, "Suggester");
        aliasSuggestionRepository.save(toVoteFor);

        // now check if it is saved in database
        Iterable<AliasSuggestion> actualAliasSuggestions = aliasSuggestionRepository.findAll();
        Iterator<AliasSuggestion> aliasIterator = actualAliasSuggestions.iterator();
        int actualAmountAliases = 0;
        AliasSuggestion actualAliasSuggestion = null;
        while (aliasIterator.hasNext()) {
            ++actualAmountAliases;
            actualAliasSuggestion = aliasIterator.next(); // now correct value should be saved in actualAliasSuggestion
        }
        assertEquals(1, actualAmountAliases);
        assertEquals(toVoteFor, actualAliasSuggestion);

        // now vote for alias suggestion
        aliasSuggestionRepository.voteNeg(toVoteFor.getMapID(), toVoteFor.getName());

        // get the updated aliasSuggestion from the database
        actualAliasSuggestions = aliasSuggestionRepository.findAll();
        aliasIterator = actualAliasSuggestions.iterator();
        actualAliasSuggestion = null;
        while (aliasIterator.hasNext()) {
            actualAliasSuggestion = aliasIterator.next(); // now correct value should be saved in actualAliasSuggestion
        }

        assertEquals(1, actualAliasSuggestion.getNegVotes());
    }

    @Test
    void whenAliasSuggestionsSaved_deleteByName() {
        // first save alias suggestions in database
        String aliasSuggestionsName = "HSaF";
        AliasSuggestion toDelete1 = new AliasSuggestion(aliasSuggestionsName, 1, "Suggester1");
        AliasSuggestion toDelete2 = new AliasSuggestion(aliasSuggestionsName, 2, "Suggester2");
        aliasSuggestionRepository.save(toDelete1);
        aliasSuggestionRepository.save(toDelete2);

        // now check if it is saved in database
        List<AliasSuggestion> actualAliasSuggestions = aliasSuggestionRepository.findAll();
        Iterator<AliasSuggestion> aliasIterator = actualAliasSuggestions.iterator();
        assertEquals(2, actualAliasSuggestions.size());
        assertEquals(toDelete1, actualAliasSuggestions.get(0));
        assertEquals(toDelete2, actualAliasSuggestions.get(1));

        // now delete the alias suggestions
        aliasSuggestionRepository.deleteByName(aliasSuggestionsName);

        List<AliasSuggestion> aliasSuggestionsSaved = aliasSuggestionRepository.findAll();
        assertTrue(aliasSuggestionsSaved.isEmpty());
    }
    
}
