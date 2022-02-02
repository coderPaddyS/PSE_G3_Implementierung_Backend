package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.repositories.BlacklistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlacklistServiceTest {

    @Autowired
    private BlacklistService blacklistService;

    @Autowired
    private BlacklistRepository blacklistRepository;

    @BeforeEach
    void setUp() {
        blacklistRepository.deleteAll();
    }
    
}
