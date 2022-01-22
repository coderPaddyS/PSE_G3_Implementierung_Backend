package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DeletedAliasRepositoryTest {

    @Autowired
    private DeletedAliasRepository deletedAliasRepository;

    @BeforeEach
    void setUp() {
        deletedAliasRepository.deleteAll();
    }

    @Test
    void whenDeletedAlias_findNewerThanVersion() {
        // save alias to database
        Alias deletedAlias = new Alias("Infobau", 1, 1);
        deletedAliasRepository.save(deletedAlias);

        // check if deletedAlias is found when version < 1
        Iterable<Alias> deletedAliases = deletedAliasRepository.findNewerThanVersion(deletedAlias.getVersion() - 1);
        Iterator<Alias> iterator = deletedAliases.iterator();
        int amountDeletedAliasesNewerVersion = 0;
        Alias actualDeletedAlias = null;
        while (iterator.hasNext()) {
            ++amountDeletedAliasesNewerVersion;
            actualDeletedAlias = iterator.next();
        }
        assertEquals(1, amountDeletedAliasesNewerVersion);
        assertEquals(deletedAlias, actualDeletedAlias);
    }
    
}
