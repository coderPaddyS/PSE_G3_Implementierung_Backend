package de.itermori.pse.kitroomfinder.backend.queryTests;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.models.DeletedAlias;
import de.itermori.pse.kitroomfinder.backend.models.User;
import de.itermori.pse.kitroomfinder.backend.models.Version;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.DeletedAliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.UserRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
import de.itermori.pse.kitroomfinder.backend.resolvers.mutationresolver.AliasMutation;
import de.itermori.pse.kitroomfinder.backend.resolvers.queryresolver.AliasQuery;
import java.io.IOException;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests {@link AliasQuery}, {@link AliasMutation} (integration test).
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AliasTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    private VersionRepository versionRepository;

    @Autowired
    private AliasRepository aliasRepository;

    @Autowired
    private DeletedAliasRepository deletedAliasRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Sets up the test resources.
     */
    @BeforeEach
    void setUp(){
        userRepository.deleteAll();
        userRepository.save(new User(UtilTests.USER, UtilTests.USER_AUTHORITY));
        userRepository.save(new User(UtilTests.ADMIN, UtilTests.ADMIN_AUTHORITY));
        aliasRepository.deleteAll();
        deletedAliasRepository.deleteAll();
        versionRepository.deleteAll();
    }

    /**
     * Cleans up the test resources.
     */
    @AfterEach
    void cleanUp() {
        userRepository.deleteAll();
    }

    /**
     * Tests the method {@link AliasQuery#getAlias(int)}.
     */
    @Test
    void getAliasTest() {
        try {
            String testname = "getAlias";
            aliasRepository.save(new Alias("alias", 1, "50.34", 1));
            UtilTests.validate(graphQLTestTemplate, testname);
        } catch (IOException | JSONException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tests the method {@link AliasMutation#removeAlias(String, int)}.
     */
    @Test
    void removeAliasTest() {
        try {
            String testname = "removeAlias";
            aliasRepository.save(new Alias("dalias", 1, "50.34", 1));
            UtilTests.validate(graphQLTestTemplate, testname, UtilTests.ADMIN);
        } catch (IOException | JSONException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tests the method {@link AliasQuery#getNewAliases(int)}.
     */
    @Test
    void getNewAliasesTest() {
        try {
            String testname = "getNewAliases";
            aliasRepository.save(new Alias("alias2", 1, "50.34", 2));
            UtilTests.validate(graphQLTestTemplate, testname);
        } catch (IOException | JSONException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tests the method {@link AliasQuery#getNewDeletedAliases(int)}.
     */
    @Test
    void getNewDeletedAliasesTest() throws JSONException, IOException {
        try {
            String testname = "getNewDeletedAliases";
            deletedAliasRepository.save(new DeletedAlias("dalias2", 1, 2));
            UtilTests.validate(graphQLTestTemplate, testname);
        } catch (IOException | JSONException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tests the method {@link AliasQuery#getVersion()}.
     */
    @Test
    void getVersionNotInitiated() {
        try {
            String testname = "getVersion";
            UtilTests.validate(graphQLTestTemplate, testname);
        } catch (IOException | JSONException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tests the method {@link AliasQuery#getVersion()}.
     */
    @Test
    void getVersionInitiated() {
        try {
            String testname = "getVersionInitiated";
            versionRepository.save(new Version(1));
            versionRepository.incrementVersion();
            UtilTests.validate(graphQLTestTemplate, testname);
        } catch (IOException | JSONException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tests the method {@link AliasQuery#getAmountEntriesAlias()} ()}
     * @throws JSONException
     * @throws IOException
     */
    @Test
    void getAmountEntriesAliasTest() throws JSONException, IOException {
        String testname = "getAmountEntriesAlias";
        aliasRepository.save(new Alias("alias1", 1, "50.34", 0));
        aliasRepository.save(new Alias("alias2", 2, "50.35", 0));
        UtilTests.validate(graphQLTestTemplate, testname, UtilTests.ADMIN);
    }

    /**
     * Tests the method {@link AliasQuery#getAllAliases()}
     * @throws JSONException
     * @throws IOException
     */
    @Test
    void getAllAliasesTest() throws JSONException, IOException {
        String testname = "getAllAliases";
        aliasRepository.save(new Alias("alias1", 1, "50.34", 0));
        aliasRepository.save(new Alias("alias2", 2, "50.35", 0));
        UtilTests.validate(graphQLTestTemplate, testname, UtilTests.ADMIN);
    }

    /**
     * Tests the method {@link AliasQuery#getAliasByName(String)}
     * @throws JSONException
     * @throws IOException
     */
    @Test
    void getAliasByNameTest() throws JSONException, IOException {
        String testname = "getAliasByName";
        aliasRepository.save(new Alias("alias1", 1, "50.34", 0));
        aliasRepository.save(new Alias("alias2", 2, "50.35", 0));
        UtilTests.validate(graphQLTestTemplate, testname, UtilTests.ADMIN);
    }
}
