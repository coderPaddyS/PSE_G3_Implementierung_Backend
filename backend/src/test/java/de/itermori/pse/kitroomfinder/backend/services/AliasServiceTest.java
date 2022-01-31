package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.repositories.AliasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AliasServiceTest {

    @Autowired
    private AliasService aliasService;

    @Autowired
    private AliasRepository aliasRepository;

    @BeforeEach
    void setUp() {
        aliasRepository.deleteAll();
    }

}


