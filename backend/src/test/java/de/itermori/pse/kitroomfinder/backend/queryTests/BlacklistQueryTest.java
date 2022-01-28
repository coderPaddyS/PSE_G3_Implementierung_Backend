package de.itermori.pse.kitroomfinder.backend.queryTests;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import de.itermori.pse.kitroomfinder.backend.models.BlacklistEntry;
import de.itermori.pse.kitroomfinder.backend.models.User;
import de.itermori.pse.kitroomfinder.backend.queryTests.UtilTests;
import de.itermori.pse.kitroomfinder.backend.repositories.BlacklistRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.UserRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
import de.itermori.pse.kitroomfinder.backend.security.JWTPreAuthenticationToken;
import de.itermori.pse.kitroomfinder.backend.services.*;
import io.micrometer.core.instrument.util.IOUtils;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlacklistQueryTest {

    private BlacklistEntry blacklistEntry = new BlacklistEntry("testEntry");

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    BlacklistService blacklistService;

    @Autowired
    BlacklistRepository blacklistRepository;

    @Autowired
    UserRepository userRepository;

    @AfterEach
    void cleanup() {
        userRepository.deleteAll();
    }

    @BeforeEach
    void setup() {
        blacklistRepository.deleteAll();
        userRepository.save(new User(UtilTests.USER, UtilTests.USER_AUTHORITY));
        userRepository.save(new User(UtilTests.ADMIN, UtilTests.ADMIN_AUTHORITY));
    }

    @Test
    public void getBlacklistTest() throws IOException, JSONException {

        String testname = "getBlacklist";
        blacklistService.addToBlacklist("testEntry1");
        blacklistService.addToBlacklist("testEntry2");
        UtilTests.validate(graphQLTestTemplate, testname, UtilTests.ADMIN);
    }

    @Test
    public void blacklistAliasTest() throws IOException, JSONException {

        String testname = "blacklistAlias";
        UtilTests.validate(graphQLTestTemplate, testname, UtilTests.ADMIN);
    }

    @Test
    public void removeFromBlacklist() throws JSONException, IOException {

        String testname = "removeFromBlacklist";
        blacklistService.addToBlacklist("badWord");
        UtilTests.validate(graphQLTestTemplate, testname, UtilTests.ADMIN);
    }


}
