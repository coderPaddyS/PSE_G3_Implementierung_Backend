package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

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
        Assertions.assertEquals(expectedAliasSuggestion, actualAliasSuggestion);
    }
}
