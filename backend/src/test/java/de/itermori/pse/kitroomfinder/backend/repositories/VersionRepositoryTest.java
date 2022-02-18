package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.Version;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link VersionRepository}.
 *
 * @author Adriano Castro
 * @version 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VersionRepositoryTest {

    @Autowired
    private VersionRepository versionRepository;

    /**
     * Sets the test resources up.
     */
    @BeforeEach
    void setUp() {
        versionRepository.deleteAll();
    }

    /**
     * Tests the method {@link VersionRepository#incrementVersion()}.
     */
    @Test
    void whenVersionAtCertainValue_thenIncrementVersion() {
        // save version with start value 1 to database
        int currentVersion = 1;
        Version version = new Version(currentVersion);
        versionRepository.save(version);

        // increment version
        versionRepository.incrementVersion();

        // get updated value of saved version
        List<Version> versions = versionRepository.findAll();

        // check if exactly the version previously saved above is in the database
        assertEquals(1, versions.size());
        assertEquals(version, versions.get(0));

        // check if version was incremented
        assertEquals(++currentVersion, versions.get(0).getCurrentVersion());

    }

    /**
     * Tests the method {@link VersionRepository#retrieveCurrentVersion()}.
     */
    @Test
    void whenVersionSaved_thenRetrieveCurrentVersion() {
        // save version to database
        int currentVersion = 1;
        Version version = new Version(currentVersion);
        versionRepository.save(version);

        // check if version saved is correct
        assertEquals(version.getCurrentVersion(), versionRepository.retrieveCurrentVersion());
    }

}
