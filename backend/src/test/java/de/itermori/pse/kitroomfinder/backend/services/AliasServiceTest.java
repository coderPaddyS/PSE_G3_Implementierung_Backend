package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void whenAliasNotYetAdded_thenAddAlias() {
        Alias expectedAlias = new Alias("Infobau", 1, 1);
        aliasService.addAlias(expectedAlias.getName(), expectedAlias.getMapID());
        Alias actualAlias = aliasRepository.findByName(expectedAlias.getName());
        assertEquals(expectedAlias, actualAlias);
    }

}


