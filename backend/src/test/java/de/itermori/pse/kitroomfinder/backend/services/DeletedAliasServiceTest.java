package de.itermori.pse.kitroomfinder.backend.services;

import com.google.common.collect.Iterables;
import de.itermori.pse.kitroomfinder.backend.models.DeletedAlias;
import de.itermori.pse.kitroomfinder.backend.repositories.DeletedAliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeletedAliasServiceTest {

    @Autowired
    DeletedAliasService deletedAliasService;

    @Autowired
    DeletedAliasRepository deletedAliasRepository;

    @Autowired
    VersionRepository versionRepository;

    @BeforeEach
    public void setup() {
        deletedAliasRepository.deleteAll();
        versionRepository.deleteAll();
    }

    @Test
    public void addDeletedAliasTest() {
        deletedAliasService.addDeletedAlias("Deleted", 1);
        assertTrue(deletedAliasRepository.findNewerThanVersion(0) != null);
    }

    @Test
    public void removeDeletedAliasTest() {
        deletedAliasService.addDeletedAlias("Deleted", 1);
        deletedAliasService.removeDeletedAlias("Deleted", 1);
        assertTrue(deletedAliasRepository.findNewerThanVersion(0) == null);
    }

    @Test
    public void addGetRemoveDeletedAliasTest() {
        deletedAliasService.addDeletedAlias("Deleted", 1);
        assertTrue(deletedAliasRepository.findNewerThanVersion(0) != null);
        DeletedAlias deletedAlias = Iterables.getOnlyElement(deletedAliasService.getDeletedAlias(0));
        assertTrue(deletedAlias.getName().equals("Deleted") && deletedAlias.getMapID().equals(1));
        deletedAliasService.removeDeletedAlias("Deleted", 1);
        assertTrue(deletedAliasRepository.findNewerThanVersion(0).spliterator().getExactSizeIfKnown() == 0);
    }
}
