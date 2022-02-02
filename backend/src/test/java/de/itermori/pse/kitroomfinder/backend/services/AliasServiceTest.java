package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(aliasService.addAlias("Infobau", 1));
        Alias actualAlias = aliasRepository.findByName("Infobau");
        assertEquals("Infobau", actualAlias.getName());
        assertEquals(1, actualAlias.getMapID());
    }

    @Test
    void whenAliasAlreadyAdded_thenAddAlias() {
        // add alias once
        assertTrue(aliasService.addAlias("Infobau", 1));
        Alias actualAlias = aliasRepository.findByName("Infobau");
        assertEquals("Infobau", actualAlias.getName());
        assertEquals(1, actualAlias.getMapID());

        // add alias a second time
        assertFalse(aliasService.addAlias("Infobau", 1));
    }

}


