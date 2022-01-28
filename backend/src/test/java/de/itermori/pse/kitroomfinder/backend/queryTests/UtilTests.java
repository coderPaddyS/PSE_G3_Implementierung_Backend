package de.itermori.pse.kitroomfinder.backend.queryTests;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import io.micrometer.core.instrument.util.IOUtils;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class UtilTests {

    static final String GRAPHQL_QUERY_REQUEST_PATH = "graphql/resolver/query/request/%s.graphql";
    static final String GRAPHQL_QUERY_RESPONSE_PATH = "graphql/resolver/query/response/%s.json";


    private static String read(String location) throws IOException {
        return IOUtils.toString(new ClassPathResource(location).getInputStream(),
                StandardCharsets.UTF_8);
    }

    public static void validate(GraphQLTestTemplate graphQLTestTemplate, String testname) throws IOException, JSONException {
        compare(graphQLTestTemplate, testname);
    }

    public static void validate(GraphQLTestTemplate graphQLTestTemplate, String testname, String user) throws IOException, JSONException {
        String token = JWT
                .create()
                .withIssuer("my-graphql-api")
                .withIssuedAt(Calendar.getInstance().getTime())
                .withExpiresAt(new Date(Calendar.getInstance().getTime().getTime() + 600000))
                .withSubject(user)
                .sign(Algorithm.HMAC256("secret"));
        graphQLTestTemplate.withBearerAuth(token);
        compare(graphQLTestTemplate, testname);
    }

    private static void compare(GraphQLTestTemplate graphQLTestTemplate, String testname) throws IOException, JSONException {
        String expectedResultBody = read(format(GRAPHQL_QUERY_RESPONSE_PATH, testname));
        GraphQLResponse response = graphQLTestTemplate.postForResource(format(GRAPHQL_QUERY_REQUEST_PATH, testname));
        String test = response.getRawResponse().getBody();
        assertThat(response.isOk()).isTrue();
        JSONAssert.assertEquals(expectedResultBody, response.getRawResponse().getBody(), true);
    }

}
