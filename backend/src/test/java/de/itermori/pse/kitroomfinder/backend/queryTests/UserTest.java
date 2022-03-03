package de.itermori.pse.kitroomfinder.backend.queryTests;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import de.itermori.pse.kitroomfinder.backend.models.User;
import de.itermori.pse.kitroomfinder.backend.repositories.UserRepository;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static de.itermori.pse.kitroomfinder.backend.queryTests.UtilTests.GRAPHQL_QUERY_REQUEST_PATH;
import static java.lang.String.format;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @AfterEach
    void cleanup() {
        userRepository.deleteAll();
    }

    @BeforeEach
    void setup() {
        userRepository.save(new User(UtilTests.ADMIN, UtilTests.ADMIN_AUTHORITY));
    }

    @Test
    public void isAdminTest() throws JSONException, IOException {
        String testname = "isAdmin";
        UtilTests.validate(graphQLTestTemplate, testname, UtilTests.ADMIN);
    }

    @Test
    public void registerUserTest() throws IOException {
        String testname = "registerUser";
        String query = "mutation Register($token : String){\n" +
                "    registerUser(accessToken: $token)\n" +
                "}";
        String token = JWT
                .create()
                .withIssuer("my-graphql-api")
                .withIssuedAt(Calendar.getInstance().getTime())
                .withExpiresAt(new Date(Calendar.getInstance().getTime().getTime() + 10000))
                .withClaim("preferred_username", "testUser")
                .sign(Algorithm.HMAC256("secret"));
        GraphQLResponse response = graphQLTestTemplate.postMultipart(query, "{\"token\" : \"" + token + "\"}");
        assertTrue(userRepository.findByName("testUser") != null);
    }
}
