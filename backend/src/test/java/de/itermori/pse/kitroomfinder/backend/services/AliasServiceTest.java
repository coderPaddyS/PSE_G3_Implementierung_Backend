package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.repositories.AliasRepository;
import de.itermori.pse.kitroomfinder.backend.services.AliasService;
import de.itermori.pse.kitroomfinder.backend.services.AliasServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AliasServiceTest {

    @Autowired
    private AliasServiceImp aliasService;

    @Test
    void test() {
        aliasService.addAlias("alias", 1);
    }


}


