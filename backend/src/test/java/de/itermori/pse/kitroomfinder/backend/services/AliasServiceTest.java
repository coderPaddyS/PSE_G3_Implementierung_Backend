package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link AliasService}.
 *
 * @author Adriano Castro
 * @version 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AliasServiceTest {

    @Autowired
    private AliasService aliasService;

    @Autowired
    private AliasRepository aliasRepository;

    /**
     * Sets the test resources up.
     */
    @BeforeEach
    void setUp() {
        aliasRepository.deleteAll();
    }

    /**
     * Tests the method {@link AliasService#addAlias(String, int)}.
     */
    @Test
    void whenAliasNotYetAdded_thenAddAlias() {
        assertTrue(aliasService.addAlias("Infobau", 1));
        Alias actualAlias = aliasRepository.findByName("Infobau");
        assertEquals("Infobau", actualAlias.getName());
        assertEquals(1, actualAlias.getMapID());
    }

    /**
     * Tests the method {@link AliasService#addAlias(String, int)}.
     */
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

    /**
     * Tests the method {@link AliasService#getAlias(int)}.
     */
    @Test
    void whenAliasesSaved_thenGetAlias() {
        // save aliases
        Alias toSave1 = new Alias("Infobau", 1, "50.34", 1);
        Alias toSave2 = new Alias("HSaF", 1, "50.34", 1);
        aliasRepository.save(toSave1);
        aliasRepository.save(toSave2);

        // get aliases and save them in a list for simpler testing
        Iterable<Alias> aliasIterable = aliasService.getAlias(1);
        Iterator<Alias> iterator = aliasIterable.iterator();
        List<Alias> aliases = new ArrayList<>();
        while (iterator.hasNext()) {
            aliases.add(iterator.next());
        }

        // assert correct aliases are retrieved
        assertEquals(2, aliases.size());
        assertEquals(toSave1, aliases.get(0));
        assertEquals(toSave2, aliases.get(1));
    }

    /**
     * Tests the method {@link AliasService#getAliasUpdates(int)}.
     */
    @Test
    void whenNewerAliasSaved_thenGetAliasUpdates() {
        // save alias
        Alias toSave = new Alias("Infobau", 1, "50.34", 1);
        aliasRepository.save(toSave);

        // get aliases and save them in a list for simpler testing
        Iterable<Alias> newAliasIterable = aliasService.getAliasUpdates(0);
        Iterator<Alias> iterator = newAliasIterable.iterator();
        List<Alias> aliases = new ArrayList<>();
        while (iterator.hasNext()) {
            aliases.add(iterator.next());
        }

        // assert correct alias is retrieved
        assertEquals(1, aliases.size());
        assertEquals(toSave, aliases.get(0));
    }

    /**
     * Tests the method {@link AliasService#getAliasUpdates(int)}.
     */
    @Test
    void whenNotNewerAliasSaved_thenGetAliasUpdates() {
        // save alias
        Alias toSave = new Alias("Infobau", 1, "50.34", 1);
        aliasRepository.save(toSave);

        // get aliases and save them in a list for simpler testing
        Iterable<Alias> newAliasIterable = aliasService.getAliasUpdates(1);
        Iterator<Alias> iterator = newAliasIterable.iterator();
        List<Alias> aliases = new ArrayList<>();
        while (iterator.hasNext()) {
            aliases.add(iterator.next());
        }

        // assert correct alias is retrieved
        assertEquals(0, aliases.size());
    }

    /**
     * Tests the method {@link AliasService#removeAlias(String)}.
     */
    @Test
    void whenAliasSaved_thenRemoveAlias() {
        // save alias
        Alias toDelete = new Alias("Infobau", 1, "50.34", 1);
        aliasRepository.save(toDelete);

        // check if alias is saved
        List<Alias> savedAliases = aliasRepository.findAll();
        assertEquals(1, savedAliases.size());
        assertEquals(toDelete, savedAliases.get(0));

        // now remove saved alias
        assertTrue(aliasService.removeAlias("Infobau"));

        // check if alias was removed
        savedAliases = aliasRepository.findAll();
        assertEquals(0, savedAliases.size());
    }

}
