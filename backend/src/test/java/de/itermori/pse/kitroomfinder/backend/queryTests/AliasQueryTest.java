package de.itermori.pse.kitroomfinder.backend.queryTests;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.models.BlacklistEntry;
import de.itermori.pse.kitroomfinder.backend.models.DeletedAlias;
import de.itermori.pse.kitroomfinder.backend.models.Version;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.DeletedAliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
import de.itermori.pse.kitroomfinder.backend.services.*;
import jdk.jshell.execution.Util;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AliasQueryTest {

    private BlacklistEntry blacklistEntry = new BlacklistEntry("testEntry");

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    AliasService aliasService;

    @Autowired
    DeletedAliasService deletedAliasService;

    @Autowired
    BlacklistService blacklistService;

    @Autowired
    VersionRepository versionRepository;

    @Autowired
    AliasRepository aliasRepository;

    @Autowired
    DeletedAliasRepository deletedAliasRepository;

    @BeforeEach
    void setUp(){
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
        UtilTests.validate(graphQLTestTemplate, testname);
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
