package de.itermori.pse.kitroomfinder.backend.queryTests;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.models.BlacklistEntry;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
import de.itermori.pse.kitroomfinder.backend.services.*;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;

public class AliasTest {

    private BlacklistEntry blacklistEntry = new BlacklistEntry("testEntry");

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    UserService userService;

    @MockBean
    AliasService aliasService;

    @MockBean
    AliasSuggestionService aliasSuggestionService;

    @MockBean
    DeletedAliasService deletedAliasService;

    @MockBean
    BlacklistService blacklistService;

    @MockBean
    MapIDService mapIDService;

    @MockBean
    VersionRepository versionRepository;

    @BeforeAll
    static void setUp(){

    }

    @Test
    public void getAliasTest() throws IOException, JSONException {

        String testname = "getAlias";

        doReturn(new Alias("Name", 1, 1)).when(blacklistService).getBlacklist();


        UtilTests.validate(graphQLTestTemplate, testname);
    }

}
