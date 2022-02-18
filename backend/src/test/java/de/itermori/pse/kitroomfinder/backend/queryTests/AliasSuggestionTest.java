package de.itermori.pse.kitroomfinder.backend.queryTests;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import de.itermori.pse.kitroomfinder.backend.models.User;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasSuggestionRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.DeletedAliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.UserRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
import de.itermori.pse.kitroomfinder.backend.resolvers.queryresolver.AliasSuggestionQuery;
import de.itermori.pse.kitroomfinder.backend.resolvers.mutationresolver.AliasSuggestionMutation;
import de.itermori.pse.kitroomfinder.backend.services.AliasService;
import de.itermori.pse.kitroomfinder.backend.services.AliasSuggestionService;
import de.itermori.pse.kitroomfinder.backend.services.BlacklistService;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static de.itermori.pse.kitroomfinder.backend.queryTests.UtilTests.ADMIN;
import static de.itermori.pse.kitroomfinder.backend.queryTests.UtilTests.GRAPHQL_QUERY_REQUEST_PATH;
import static de.itermori.pse.kitroomfinder.backend.queryTests.UtilTests.USER;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests {@link AliasSuggestion} (integration test).
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AliasSuggestionTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    private AliasService aliasService;

    @Autowired
    private AliasSuggestionService aliasSuggestionService;

    @Autowired
    private BlacklistService blacklistService;

    @Autowired
    private VersionRepository versionRepository;

    @Autowired
    private AliasRepository aliasRepository;

    @Autowired
    private DeletedAliasRepository deletedAliasRepository;

    @Autowired
    private AliasSuggestionRepository aliasSuggestionRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Sets up the test resources.
     */
    @BeforeEach
    void setUp(){
        userRepository.save(new User(UtilTests.USER, UtilTests.USER_AUTHORITY));
        userRepository.save(new User(UtilTests.ADMIN, UtilTests.ADMIN_AUTHORITY));
        aliasRepository.deleteAll();
        aliasSuggestionRepository.deleteAll();
    }

    /**
     * Cleans up the test resources.
     */
    @AfterEach
    void cleanUp() {
        userRepository.deleteAll();
    }

    /**
     * Tests the method {@link AliasSuggestionQuery#getAliasSuggestions(int, int)}.
     */
    @Test
    void getAliasSuggestionsTest() {
        try {
            String testname = "getAliasSuggestions";
            aliasSuggestionRepository.save(new AliasSuggestion("suggestion", 1, "50.34", "user1"));
            aliasSuggestionRepository.votePos(1, "suggestion");
            aliasSuggestionRepository.voteNeg(1, "suggestion");
            UtilTests.validate(graphQLTestTemplate, testname, ADMIN);
        } catch (IOException | JSONException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tests the method {@link AliasSuggestionQuery#getAliasSuggestionsAmount(int, int, String)}.
     */
    @Test
    void getAliasSuggestionAmountTest() {
        try {
            String testname = "getAliasSuggestionAmount";
            aliasSuggestionRepository.save(new AliasSuggestion("a", 1, "50.34", "sug"));
            aliasSuggestionRepository.save(new AliasSuggestion("b", 1, "50.34", "sug"));
            aliasSuggestionRepository.save(new AliasSuggestion("c", 1, "50.34", "sug"));
            aliasSuggestionService.voteForAlias("a", 1, "user", true);
            aliasSuggestionService.voteForAlias("a", 1, "user2", false);
            aliasSuggestionService.voteForAlias("a", 1, "user3", false);
            UtilTests.validate(graphQLTestTemplate, testname, USER);
        } catch (IOException | JSONException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tests the method {@link AliasSuggestionMutation#suggestAlias(String, int, String)}.
     */
    @Test
    void suggestAlias() throws IOException {
        try {
            String testname = "suggestAlias";
            String token = JWT
                    .create()
                    .withIssuer("my-graphql-api")
                    .withIssuedAt(Calendar.getInstance().getTime())
                    .withExpiresAt(new Date(Calendar.getInstance().getTime().getTime() + 600000))
                    .withClaim("preferred_username", "user")
                    .sign(Algorithm.HMAC256("secret"));
            graphQLTestTemplate.withBearerAuth(token);
            graphQLTestTemplate.postForResource(format(GRAPHQL_QUERY_REQUEST_PATH, testname));
            assertNotNull(aliasSuggestionRepository.findByNameAndMapID("alias", 1));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tests the method {@link AliasSuggestionMutation#disapproveAliasSuggestion(String, int)}.
     */
    @Test
    void removeAliasSuggestionTest() {
        try {
            String testname = "removeAliasSuggestion";
            String token = JWT
                    .create()
                    .withIssuer("my-graphql-api")
                    .withIssuedAt(Calendar.getInstance().getTime())
                    .withExpiresAt(new Date(Calendar.getInstance().getTime().getTime() + 600000))
                    .withClaim("preferred_username", "user")
                    .sign(Algorithm.HMAC256("secret"));
            graphQLTestTemplate.withBearerAuth(token);
            graphQLTestTemplate.postForResource(format(GRAPHQL_QUERY_REQUEST_PATH, testname));
            assertNull(aliasSuggestionRepository.findByNameAndMapID("alias", 1));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tests the method {@link AliasSuggestionMutation#voteForAliasSuggestion(String, int, String, Boolean)}.
     */
    @Test
    void voteForAliasTest() {
        try {
            String testname = "voteForAlias";

            aliasSuggestionRepository.save(new AliasSuggestion("alias", 1, "50.34", "suggester"));
            String token = JWT
                    .create()
                    .withIssuer("my-graphql-api")
                    .withIssuedAt(Calendar.getInstance().getTime())
                    .withExpiresAt(new Date(Calendar.getInstance().getTime().getTime() + 600000))
                    .withClaim("preferred_username", "user")
                    .sign(Algorithm.HMAC256("secret"));
            graphQLTestTemplate.withBearerAuth(token);
            graphQLTestTemplate.postForResource(format(GRAPHQL_QUERY_REQUEST_PATH, testname));
            int i = aliasSuggestionRepository.findByNameAndMapID("alias", 1).getPosVotes();
            assertEquals(1, (int) aliasSuggestionRepository
                    .findByNameAndMapID("alias", 1).getPosVotes());
        } catch (IOException e) {
            fail(e.getMessage());
        }

    }
