package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
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

    @Test
    void whenAliasSuggestionSaved_findByNameAndMapID() {
        AliasSuggestion expectedAliasSuggestion = new AliasSuggestion("HSaF", 1, "Suggester");
        aliasSuggestionRepository.save(expectedAliasSuggestion);
        AliasSuggestion actualAliasSuggestion = aliasSuggestionRepository.findByNameAndMapID("HSaF", 1);
        assertEquals(expectedAliasSuggestion, actualAliasSuggestion);
    }

    @Test
    void whenAliasSuggestionSaved_deleteByNameAndID() {
        aliasSuggestionRepository.deleteAll();
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

        assertEquals(1, toVoteFor.getPosVotes());
    }
}
