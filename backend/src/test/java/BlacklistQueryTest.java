
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import de.itermori.pse.kitroomfinder.backend.models.BlacklistEntry;
import de.itermori.pse.kitroomfinder.backend.repositories.BlacklistRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;

@GraphQLTest
public class BlacklistQueryTest {

    private BlacklistEntry blacklistEntry = new BlacklistEntry("testEntry");

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    BlacklistRepository blacklistRepositoryMock;

    @BeforeAll
    static void setUp(){

    }

    @Test
    public void getBlacklistTest() throws IOException {
        doReturn(blacklistEntry).when(blacklistRepositoryMock).findAll();
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/getBlacklist.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.getBlacklist")).isEqualTo("testEntry");
    }









}
