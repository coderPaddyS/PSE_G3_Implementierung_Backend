package de.itermori.pse.kitroomfinder.backend.queryTests;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import de.itermori.pse.kitroomfinder.backend.models.*;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasSuggestionRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.DeletedAliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
import de.itermori.pse.kitroomfinder.backend.services.AliasService;
import de.itermori.pse.kitroomfinder.backend.services.AliasSuggestionService;
import de.itermori.pse.kitroomfinder.backend.services.BlacklistService;
import de.itermori.pse.kitroomfinder.backend.services.DeletedAliasService;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AliasSuggestionTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    AliasService aliasService;

    @Autowired
    AliasSuggestionService aliasSuggestionService;

    @Autowired
    BlacklistService blacklistService;

    @Autowired
    VersionRepository versionRepository;

    @Autowired
    AliasRepository aliasRepository;

    @Autowired
    DeletedAliasRepository deletedAliasRepository;

    @Autowired
    AliasSuggestionRepository aliasSuggestionRepository;

    @BeforeEach
    void setUp(){
        aliasRepository.deleteAll();
        aliasSuggestionRepository.deleteAll();
    }

    @Test
    public void getAliasSuggestionsTest() throws IOException, JSONException {
        String testname = "getAliasSuggestions";
        aliasSuggestionRepository.save(new AliasSuggestion("suggestion", 1, "user1"));
        aliasSuggestionRepository.votePos(1, "suggestion");
        aliasSuggestionRepository.voteNeg(1, "suggestion");
        UtilTests.validate(graphQLTestTemplate, testname);
    }

    @Test
    public void test()
    {
        aliasSuggestionRepository.save(new AliasSuggestion("a", 1 , "sug"));
        aliasSuggestionRepository.save(new AliasSuggestion("b", 1 , "sug"));
        aliasSuggestionService.voteForAlias("a", 1, "user", true);
        aliasSuggestionService.voteForAlias("a", 1, "user2", false);

        Iterable<AliasSuggestion> result = aliasSuggestionService.getAliasSuggestionsAmount(1,1, "user");
        int i = 5;
    }
}
