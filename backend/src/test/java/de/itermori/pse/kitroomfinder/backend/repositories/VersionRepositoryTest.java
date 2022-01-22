package de.itermori.pse.kitroomfinder.backend.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VersionRepositoryTest {

    @Autowired
    private VersionRepository versionRepository;

    @BeforeEach
    void setUp() {
        versionRepository.deleteAll();
    }
    
}
