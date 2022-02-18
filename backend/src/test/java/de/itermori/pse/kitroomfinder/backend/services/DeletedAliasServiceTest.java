package de.itermori.pse.kitroomfinder.backend.services;

import com.google.common.collect.Iterables;
import de.itermori.pse.kitroomfinder.backend.models.DeletedAlias;
import de.itermori.pse.kitroomfinder.backend.repositories.DeletedAliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link DeletedAliasService}.
 *
 * @author Lukas Zetto
 * @version 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DeletedAliasServiceTest {

    @Autowired
    private DeletedAliasService deletedAliasService;

    @Autowired
    private DeletedAliasRepository deletedAliasRepository;

    @Autowired
    private VersionRepository versionRepository;

    /**
     * Sets the test resources up.
     */
    @BeforeEach
    public void setUp() {
        deletedAliasRepository.deleteAll();
        versionRepository.deleteAll();
    }

    /**
     * Tests the method {@link DeletedAliasService#addDeletedAlias(String, int)}.
     */
    @Test
    void addDeletedAliasTest() {
        deletedAliasService.addDeletedAlias("Deleted", 1);
        assertNotNull(deletedAliasRepository.findNewerThanVersion(0));
    }

    /**
     * Tests the method {@link DeletedAliasService#removeDeletedAlias(String, int)}.
     */
    @Test
    void removeDeletedAliasTest() {
        deletedAliasService.addDeletedAlias("Deleted", 1);
        deletedAliasService.removeDeletedAlias("Deleted", 1);
        assertEquals(0, deletedAliasRepository.findNewerThanVersion(0).spliterator().getExactSizeIfKnown());
    }

    /**
     * Tests the method {@link DeletedAliasService#removeDeletedAlias(String, int)}.
     */
    @Test
    void addGetRemoveDeletedAliasTest() {
        deletedAliasService.addDeletedAlias("Deleted", 1);
        assertNotNull(deletedAliasRepository.findNewerThanVersion(0));
        DeletedAlias deletedAlias = Iterables.getOnlyElement(deletedAliasService.getDeletedAlias(0));
        assertTrue(deletedAlias.getName().equals("Deleted") && deletedAlias.getMapID().equals(1));
        deletedAliasService.removeDeletedAlias("Deleted", 1);
        assertEquals(0, deletedAliasRepository.findNewerThanVersion(0).spliterator().getExactSizeIfKnown());
    }

}
