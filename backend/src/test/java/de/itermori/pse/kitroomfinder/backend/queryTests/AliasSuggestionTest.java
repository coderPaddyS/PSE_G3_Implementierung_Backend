package de.itermori.pse.kitroomfinder.backend.queryTests;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import de.itermori.pse.kitroomfinder.backend.models.*;
import de.itermori.pse.kitroomfinder.backend.repositories.*;

import static de.itermori.pse.kitroomfinder.backend.queryTests.UtilTests.*;
import static org.junit.jupiter.api.Assertions.*;
import de.itermori.pse.kitroomfinder.backend.security.JWTPreAuthenticationToken;
import de.itermori.pse.kitroomfinder.backend.services.AliasService;
import de.itermori.pse.kitroomfinder.backend.services.AliasSuggestionService;
import de.itermori.pse.kitroomfinder.backend.services.BlacklistService;
import de.itermori.pse.kitroomfinder.backend.services.DeletedAliasService;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;
import static org.mockito.Mockito.doReturn;

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

    @Autowired
    UserRepository userRepository;

    @AfterEach
    void cleanUp() {
       userRepository.deleteAll();
    }

    @BeforeEach
    void setUp(){
        userRepository.save(new User(UtilTests.USER, UtilTests.USER_AUTHORITY));
        userRepository.save(new User(UtilTests.ADMIN, UtilTests.ADMIN_AUTHORITY));
        aliasRepository.deleteAll();
        aliasSuggestionRepository.deleteAll();
    }

    @Test
    public void getAliasSuggestionsTest() throws IOException, JSONException {
        String testname = "getAliasSuggestions";
        aliasSuggestionRepository.save(new AliasSuggestion("suggestion", 1, "50.34", "user1"));
        aliasSuggestionRepository.votePos(1, "suggestion");
        aliasSuggestionRepository.voteNeg(1, "suggestion");
        UtilTests.validate(graphQLTestTemplate, testname, ADMIN);
    }

    @Test
    public void getAliasSuggestionAmountTest() throws JSONException, IOException {
        String testname = "getAliasSuggestionAmount";
        aliasSuggestionRepository.save(new AliasSuggestion("a", 1 , "50.34", "sug"));
        aliasSuggestionRepository.save(new AliasSuggestion("b", 1 , "50.34", "sug"));
        aliasSuggestionRepository.save(new AliasSuggestion("c", 1 , "50.34", "sug"));
        aliasSuggestionService.voteForAlias("a", 1, "user", true);
        aliasSuggestionService.voteForAlias("a", 1, "user2", false);
        aliasSuggestionService.voteForAlias("a", 1, "user3", false);
        UtilTests.validate(graphQLTestTemplate, testname, USER);
    }

    @Test
    public void suggestAlias() throws IOException {
        String testname = "suggestAlias";
        graphQLTestTemplate.postForResource(format(GRAPHQL_QUERY_REQUEST_PATH, testname));
        assertTrue(aliasSuggestionRepository.findByNameAndMapID("alias", 1) != null);
    }

    @Test
    public void removeAliasSuggestionTest() throws JSONException, IOException {
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
        assertTrue(aliasSuggestionRepository.findByNameAndMapID("alias", 1) == null);
    }

    @Test
    public void voteForAliasTest() throws IOException, JSONException {
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
        assertTrue(aliasSuggestionRepository.findByNameAndMapID("alias", 1).getPosVotes() == 1);
    }
}
