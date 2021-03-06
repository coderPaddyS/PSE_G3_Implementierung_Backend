package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link AliasRepository}.
 *
 * @author Adriano Castro
 * @version 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AliasRepositoryTest {

    @Autowired
    private AliasRepository aliasRepository;

    /**
     * Sets up the test resources.
     */
    @BeforeEach
    void setUp() {
        aliasRepository.deleteAll();
    }

    /**
     * Tests the method {@link AliasRepository#findByMapID(int)}.
     */
    @Test
    void whenAliasSaved_thenFindByMapID() {
        Alias expectedAlias = new Alias("Infobau", 1, "50.34", 1);
        aliasRepository.save(expectedAlias);
        Iterable<Alias> actualAliases = aliasRepository.findByMapID(1);
        Iterator<Alias> aliasIterator = actualAliases.iterator();
        int actualAmountAliases = 0;
        Alias actualAlias = new Alias("AnotherAlias", 2, "50.34", 2);
        while (aliasIterator.hasNext()) {
            ++actualAmountAliases;
            actualAlias = aliasIterator.next(); // now correct value should be saved in actualAlias
        }
        assertEquals(1, actualAmountAliases);
        assertEquals(expectedAlias, actualAlias);
    }

    /**
     * Tests the method {@link AliasRepository#findByNameAndMapID(String, int)} .
     */
    @Test
    void whenAliasSaved_thenFindByName() {
        Alias expectedAlias = new Alias("Infobau", 1, "50.34", 1);
        aliasRepository.save(expectedAlias);
        Alias actualAlias = aliasRepository.findByNameAndMapID("Infobau", 1);
        assertEquals(expectedAlias, actualAlias);
    }

    /**
     * Tests the method {@link AliasRepository#deleteByNameAndMapID(String, int)}.
     */
    @Test
    void whenAliasSaved_thenDeleteByName() {
        // first save alias in database
        Alias toDelete = new Alias("Infobau", 1, "50.34", 1);
        aliasRepository.save(toDelete);

        // now check if it is saved in database
        Alias actualAlias = aliasRepository.findByNameAndMapID("Infobau", 1);
        assertEquals(toDelete, actualAlias);

        // now delete it
        aliasRepository.deleteByNameAndMapID(toDelete.getName(), toDelete.getMapID());

        List<Alias> aliasesSaved = aliasRepository.findAll();
        assertTrue(aliasesSaved.isEmpty());
    }

    /**
     * Tests the method {@link AliasRepository#findUpdatesByVersion(int)}.
     */
    @Test
    void whenNewerAlias_thenFindUpdatesByVersion() {
        Alias newerAlias = new Alias("Infobau", 1, "50.34", 1);
        aliasRepository.save(newerAlias);
        Iterable<Alias> newAliases = aliasRepository.findUpdatesByVersion(0);
        long actualAmountAliases = newAliases.spliterator().getExactSizeIfKnown();;
        assertEquals(1, actualAmountAliases);
        assertEquals(newerAlias, newAliases.iterator().next());
    }

}
