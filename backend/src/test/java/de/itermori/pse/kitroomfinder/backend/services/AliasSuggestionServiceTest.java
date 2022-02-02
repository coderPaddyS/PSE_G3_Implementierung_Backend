package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasSuggestionRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AliasSuggestionServiceTest {

    @Autowired
    private AliasSuggestionService aliasSuggestionService;

    @Autowired
    private AliasSuggestionRepository aliasSuggestionRepository;

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
}
