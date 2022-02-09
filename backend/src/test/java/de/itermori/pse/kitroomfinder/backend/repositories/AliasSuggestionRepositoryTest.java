package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import java.util.ArrayList;
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
    void whenAliasSuggestionSaved_thenFindByNameAndMapID() {
        AliasSuggestion expectedAliasSuggestion = new AliasSuggestion("HSaF", 1, "50.34", "Suggester");
        aliasSuggestionRepository.save(expectedAliasSuggestion);
        AliasSuggestion actualAliasSuggestion = aliasSuggestionRepository.findByNameAndMapID("HSaF", 1);
        assertEquals(expectedAliasSuggestion, actualAliasSuggestion);
    }

    @Test
    void whenAliasSuggestionSaved_thenDeleteByNameAndID() {
        // first save alias suggestion in database
        AliasSuggestion toDelete = new AliasSuggestion("HSaF", 1, "50.34", "Suggester");
        aliasSuggestionRepository.save(toDelete);

        // now check if it is saved in database
        List<AliasSuggestion> actualAliasSuggestions = aliasSuggestionRepository.findAll();
        assertEquals(1, actualAliasSuggestions.size());
        assertEquals(toDelete, actualAliasSuggestions.get(0));

        // now delete it
        aliasSuggestionRepository.deleteByNameAndID(1, toDelete.getName());

        actualAliasSuggestions = aliasSuggestionRepository.findAll();
        assertTrue(actualAliasSuggestions.isEmpty());
    }

    @Test
    void whenAliasSuggestionSaved_thenVotePos() {
        // first save alias suggestion in database
        AliasSuggestion toVoteFor = new AliasSuggestion("HSaF", 1, "50.34", "Suggester");
        aliasSuggestionRepository.save(toVoteFor);

        // now check if it is saved in database
        List<AliasSuggestion> actualAliasSuggestions = aliasSuggestionRepository.findAll();
        assertEquals(1, actualAliasSuggestions.size());
        assertEquals(toVoteFor, actualAliasSuggestions.get(0));

        // now vote for alias suggestion
        aliasSuggestionRepository.votePos(toVoteFor.getMapID(), toVoteFor.getName());

        // get the updated aliasSuggestion from the database
        actualAliasSuggestions = aliasSuggestionRepository.findAll();

        assertEquals(1, actualAliasSuggestions.size());
        assertEquals(1, actualAliasSuggestions.get(0).getPosVotes());
    }

    @Test
    void whenAliasSuggestionSaved_thenVoteNeg() {
        // first save alias suggestion in database
        AliasSuggestion toVoteFor = new AliasSuggestion("HSaF", 1, "50.34", "Suggester");
        aliasSuggestionRepository.save(toVoteFor);

        // now check if it is saved in database
        List<AliasSuggestion> actualAliasSuggestions = aliasSuggestionRepository.findAll();
        assertEquals(1, actualAliasSuggestions.size());
        assertEquals(toVoteFor, actualAliasSuggestions.get(0));

        // now vote for alias suggestion
        aliasSuggestionRepository.voteNeg(toVoteFor.getMapID(), toVoteFor.getName());

        // get the updated aliasSuggestion from the database
        actualAliasSuggestions = aliasSuggestionRepository.findAll();

        assertEquals(1, actualAliasSuggestions.size());
        assertEquals(1, actualAliasSuggestions.get(0).getNegVotes());
    }

    @Test
    void whenAliasSuggestionsSaved_thenDeleteByName() {
        // first save alias suggestions in database
        String aliasSuggestionsName = "HSaF";
        AliasSuggestion toDelete1 = new AliasSuggestion(aliasSuggestionsName, 1, "50.34", "Suggester1");
        AliasSuggestion toDelete2 = new AliasSuggestion(aliasSuggestionsName, 2, "50.34", "Suggester2");
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

    @Test
    void whenAliasSuggestionsSaved_thenFindByVotes() {
        // first save alias suggestions in database
        String aliasSuggestionsName = "HSaF";
        AliasSuggestion toVoteFor1 = new AliasSuggestion(aliasSuggestionsName, 1, "50.34", "Suggester1");
        AliasSuggestion toVoteFor2 = new AliasSuggestion(aliasSuggestionsName, 2, "50.34", "Suggester2");
        aliasSuggestionRepository.save(toVoteFor1);
        aliasSuggestionRepository.save(toVoteFor2);

        // now vote for alias suggestions
        aliasSuggestionRepository.votePos(toVoteFor1.getMapID(), toVoteFor1.getName());
        aliasSuggestionRepository.votePos(toVoteFor1.getMapID(), toVoteFor1.getName());
        aliasSuggestionRepository.voteNeg(toVoteFor1.getMapID(), toVoteFor1.getName());
        aliasSuggestionRepository.votePos(toVoteFor2.getMapID(), toVoteFor2.getName());
        aliasSuggestionRepository.voteNeg(toVoteFor2.getMapID(), toVoteFor2.getName());

        // get the updated aliasSuggestion from the database
        List<AliasSuggestion> actualAliasSuggestions = aliasSuggestionRepository.findAll();

        // check if amount of votes is correct
        assertEquals(2, actualAliasSuggestions.get(0).getPosVotes());
        assertEquals(1, actualAliasSuggestions.get(0).getNegVotes());
        assertEquals(1, actualAliasSuggestions.get(1).getPosVotes());
        assertEquals(1, actualAliasSuggestions.get(1).getNegVotes());

        // check if exactly alias suggestions toVoteFor1 and toVoteFor2 are found
        Iterable<AliasSuggestion> aliasSuggestionsPos1Neg1 = aliasSuggestionRepository.findByVotes(1, 1);
        Iterator<AliasSuggestion> aliasIterator = aliasSuggestionsPos1Neg1.iterator();
        List<AliasSuggestion> actualAliasSuggestionsPos1Neg1 = new ArrayList<>();
        while (aliasIterator.hasNext()) {
            actualAliasSuggestionsPos1Neg1.add(aliasIterator.next());
        }
        // now correct values should be saved in actualAliasSuggestionsPos1Neg1
        assertEquals(2, actualAliasSuggestionsPos1Neg1.size());
        assertEquals(toVoteFor1, actualAliasSuggestionsPos1Neg1.get(0));
        assertEquals(toVoteFor2, actualAliasSuggestionsPos1Neg1.get(1));
    }
    
}
