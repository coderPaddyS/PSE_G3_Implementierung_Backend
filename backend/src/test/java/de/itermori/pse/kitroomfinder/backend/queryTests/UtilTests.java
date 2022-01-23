package de.itermori.pse.kitroomfinder.backend.queryTests;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import io.micrometer.core.instrument.util.IOUtils;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class UtilTests {

    private static final String GRAPHQL_QUERY_REQUEST_PATH = "graphql/resolver/query/request/%s.graphql";
    private static final String GRAPHQL_QUERY_RESPONSE_PATH = "graphql/resolver/query/response/%s.json";


    private static String read(String location) throws IOException {
        return IOUtils.toString(new ClassPathResource(location).getInputStream(),
                StandardCharsets.UTF_8);
    }

    public static void validate(GraphQLTestTemplate graphQLTestTemplate, String testname) throws IOException, JSONException {
        String expectedResultBody = read(format(GRAPHQL_QUERY_RESPONSE_PATH, testname));
        GraphQLResponse response = graphQLTestTemplate.postForResource(format(GRAPHQL_QUERY_REQUEST_PATH, testname));
        String test = response.getRawResponse().getBody();
        assertThat(response.isOk()).isTrue();
        JSONAssert.assertEquals(expectedResultBody, response.getRawResponse().getBody(), true);
    }

}
