package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.repositories.AliasSuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AliasSuggestionServiceTest {

    @Autowired
    private AliasSuggestionService aliasSuggestionService;

    @Autowired
    private AliasSuggestionRepository aliasSuggestionRepository;
}
