package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.Version;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VersionRepositoryTest {

    @Autowired
    private VersionRepository versionRepository;

    @BeforeEach
    void setUp() {
        versionRepository.deleteAll();
    }

    @Test
    void whenVersionAtCertainValue_thenIncrementVersion() {
        int currentVersion = 1;
        Version version = new Version(currentVersion);
        versionRepository.incrementVersion();
        assertEquals(++currentVersion, versionRepository.retrieveCurrentVersion());
    }
}
