package de.itermori.pse.kitroomfinder.backend.queryTests;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import de.itermori.pse.kitroomfinder.backend.models.*;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.DeletedAliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.UserRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AliasQueryTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    VersionRepository versionRepository;

    @Autowired
    AliasRepository aliasRepository;

    @Autowired
    DeletedAliasRepository deletedAliasRepository;

    @Autowired
    UserRepository userRepository;

    @AfterEach
    void setAllUp() {
        userRepository.deleteAll();
    }

    @BeforeEach
    void setUp(){
        userRepository.save(new User(UtilTests.USER, UtilTests.USER_AUTHORITY));
        userRepository.save(new User(UtilTests.ADMIN, UtilTests.ADMIN_AUTHORITY));
        aliasRepository.deleteAll();
        deletedAliasRepository.deleteAll();
        versionRepository.deleteAll();
    }

    @Test
    public void getAliasTest() throws IOException, JSONException {
        String testname = "getAlias";
        aliasRepository.save(new Alias("alias", 1, 1));
        UtilTests.validate(graphQLTestTemplate, testname);
    }

    @Test
    public void removeAliasTest() throws JSONException, IOException {
        String testname = "removeAlias";
        aliasRepository.save(new Alias("dalias", 1 ,1));
        UtilTests.validate(graphQLTestTemplate, testname, UtilTests.ADMIN);
    }

    @Test
    public void getNewAliasesTest() throws JSONException, IOException {
        String testname = "getNewAliases";
        aliasRepository.save(new Alias("alias2", 1, 2));
        UtilTests.validate(graphQLTestTemplate, testname);
    }

    @Test
    public void getNewDeletedAliasesTest() throws JSONException, IOException {
        String testname = "getNewDeletedAliases";
        deletedAliasRepository.save(new DeletedAlias("dalias2", 1, 2));
        UtilTests.validate(graphQLTestTemplate, testname);
    }

    @Test
    public void getVersionNotInitiated() throws JSONException, IOException {
        String testname = "getVersion";
        UtilTests.validate(graphQLTestTemplate, testname);
    }

    @Test
    public  void getVersionInitiated() throws JSONException, IOException {
        String testname = "getVersionInitiated";
        versionRepository.save(new Version(1));
        versionRepository.incrementVersion();
        UtilTests.validate(graphQLTestTemplate, testname);
    }
}
