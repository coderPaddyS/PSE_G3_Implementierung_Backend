package de.itermori.pse.kitroomfinder.backend;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import de.itermori.pse.kitroomfinder.backend.models.BlacklistEntry;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlacklistQueryTest {

    private BlacklistEntry blacklistEntry = new BlacklistEntry("testEntry");

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;


    @BeforeAll
    static void setUp(){

    }

    @Test
    public void getBlacklistTest() throws IOException {

    }









}
