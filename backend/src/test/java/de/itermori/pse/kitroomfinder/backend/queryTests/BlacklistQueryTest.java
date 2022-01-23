package de.itermori.pse.kitroomfinder.backend.queryTests;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import de.itermori.pse.kitroomfinder.backend.models.BlacklistEntry;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
import de.itermori.pse.kitroomfinder.backend.security.JWTPreAuthenticationToken;
import de.itermori.pse.kitroomfinder.backend.services.*;
import io.micrometer.core.instrument.util.IOUtils;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import static org.mockito.Mockito.doReturn;

@GraphQLTest
public class BlacklistQueryTest {

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
    public void getBlacklistTest() throws IOException, JSONException {

        String testname = "getBlacklist";
        List<String> testList = new ArrayList<>();
        testList.add("testEntry1");
        testList.add("testEntry2");
        doReturn(testList).when(blacklistService).getBlacklist();

        UtilTests.validate(graphQLTestTemplate, testname);
    }

    @Test
    public void blacklistAliasTest() throws IOException, JSONException {

        String testname = "blacklistAlias";
        doReturn(true).when(blacklistService).addToBlacklist("badWord");
        doReturn(false).when(blacklistService).isBlacklisted("badWord");
        doReturn(true).when(aliasService).removeAlias("badWord");
        doReturn(true).when(aliasSuggestionService).removeAliasSuggestion("badWord");
        UtilTests.validate(graphQLTestTemplate, testname);

    }

    @Test
    public void removeFromBlacklist() throws JSONException, IOException {

        String testname = "removeFromBlacklist";
        doReturn(true).when(blacklistService).isBlacklisted("badWord");
        doReturn(true).when(blacklistService).removeFromBlacklist("badWord");
        UtilTests.validate(graphQLTestTemplate, testname);
    }


}
