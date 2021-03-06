package de.itermori.pse.kitroomfinder.backend.repositories;

import java.util.Iterator;
import java.util.List;

import de.itermori.pse.kitroomfinder.backend.models.DeletedAlias;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link DeletedAliasRepository}.
 *
 * @author Adriano Castro
 * @version 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DeletedAliasRepositoryTest {

    @Autowired
    private DeletedAliasRepository deletedAliasRepository;

    /**
     * Sets up the test resources.
     */
    @BeforeEach
    void setUp() {
        deletedAliasRepository.deleteAll();
    }

    /**
     * Tests the method {@link DeletedAliasRepository#findNewerThanVersion(int)}.
     */
    @Test
    void whenDeletedAlias_thenFindNewerThanVersion() {
        // save alias to database
        DeletedAlias deletedAlias = new DeletedAlias("Infobau", 1, 1);
        deletedAliasRepository.save(deletedAlias);

        // check if deletedAlias is found when version < 1
        Iterable<DeletedAlias> deletedAliases = deletedAliasRepository.findNewerThanVersion(deletedAlias.getVersion() - 1);
        Iterator<DeletedAlias> iterator = deletedAliases.iterator();
        int amountDeletedAliasesNewerVersion = 0;
        DeletedAlias actualDeletedAlias = null;
        while (iterator.hasNext()) {
            ++amountDeletedAliasesNewerVersion;
            actualDeletedAlias = iterator.next();
        }
        assertEquals(1, amountDeletedAliasesNewerVersion);
        assertEquals(deletedAlias, actualDeletedAlias);
    }

    /**
     * Tests the method {@link DeletedAliasRepository#deleteByNameAndMapID(String, int)}.
     */
    @Test
    void whenDeletedAlias_thenDeleteByNameAndMapID() {
        // save alias to database
        DeletedAlias deletedAlias = new DeletedAlias("Infobau", 1, 1);
        deletedAliasRepository.save(deletedAlias);

        // check if deletedAlias is saved in the database
        List<DeletedAlias> deletedAliases = deletedAliasRepository.findAll();
        assertEquals(1, deletedAliases.size());
        assertEquals(deletedAlias, deletedAliases.get(0));

        // delete saved alias from the database
        deletedAliasRepository.deleteByNameAndMapID(deletedAlias.getName(), deletedAlias.getMapID());

        // check if alias is no longer in the database
        deletedAliases = deletedAliasRepository.findAll();
        assertTrue(deletedAliases.isEmpty());
    }
    
}
