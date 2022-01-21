package de.itermori.pse.kitroomfinder.backend;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import de.itermori.pse.kitroomfinder.backend.models.BlacklistEntry;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
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

    private static final String GRAPHQL_QUERY_REQUEST_PATH = "graphql/resolver/query/request/%s.graphql";
    private static final String GRAPHQL_QUERY_RESPONSE_PATH = "graphql/resolver/query/response/%s.json";

    @Test
    public void getBlacklistTest() throws IOException, JSONException {

        String testname = "getBlacklist";
        List<String> testList = new ArrayList<>();
        testList.add("testEntry1");
        testList.add("testEntry2");
        doReturn(testList).when(blacklistService).getBlacklist();

        String expectedResultBody = read(format(GRAPHQL_QUERY_RESPONSE_PATH, testname));

        GraphQLResponse response = graphQLTestTemplate.postForResource(format(GRAPHQL_QUERY_REQUEST_PATH, testname));
        assertThat(response.isOk()).isTrue();
        JSONAssert.assertEquals(expectedResultBody, response.getRawResponse().getBody(), true);

    }

    private String read(String location) throws IOException {
        return IOUtils.toString(new ClassPathResource(location).getInputStream(),
                StandardCharsets.UTF_8);
    }









}
