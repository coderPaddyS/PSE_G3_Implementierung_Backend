package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
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
        // first save alias in database
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
}
