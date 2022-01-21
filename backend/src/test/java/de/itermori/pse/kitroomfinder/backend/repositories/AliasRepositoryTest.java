package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import java.util.Iterator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class AliasRepositoryTest {

    @Autowired
    private AliasRepository aliasRepository;

    @Test
    void whenAliasSaved_thenFindsByMapID() {
        Alias expectedAlias = new Alias("Infobau", 1, 1);
        aliasRepository.save(expectedAlias);
        Iterable<Alias> actualAliases = aliasRepository.findByMapID(1);
        Iterator<Alias> aliasIterator = actualAliases.iterator();
        int actualAmountAliases = 0;
        Alias actualAlias = new Alias("AnotherAlias", 2, 2);
        while (aliasIterator.hasNext()) {
            ++actualAmountAliases;
            actualAlias = aliasIterator.next(); // now correct value should be saved in actualAlias
        }
        assertEquals(1, actualAmountAliases);
        assertEquals(expectedAlias, actualAlias);
    }

    @Test
    void whenAliasSaved_thenFindsByName() {
        Alias expectedAlias = new Alias("Infobau", 1, 1);
        aliasRepository.save(expectedAlias);
        Iterable<Alias> actualAliases = aliasRepository.findByName("Infobau");
        Iterator<Alias> aliasIterator = actualAliases.iterator();
        int actualAmountAliases = 0;
        Alias actualAlias = new Alias("AnotherAlias", 2, 2);
        while (aliasIterator.hasNext()) {
            ++actualAmountAliases;
            actualAlias = aliasIterator.next(); // now correct value should be saved in actualAlias
        }
        assertEquals(1, actualAmountAliases);
        assertEquals(expectedAlias, actualAlias);
    }

    @Test
    void whenAliasSaved_thenDeleteByName() {
        // first save alias in database
        Alias toDelete = new Alias("Infobau", 1, 1);
        aliasRepository.save(toDelete);

        // now check if it is saved in database
        Iterable<Alias> actualAliases = aliasRepository.findByName("Infobau");
        Iterator<Alias> aliasIterator = actualAliases.iterator();
        int actualAmountAliases = 0;
        Alias actualAlias = new Alias("AnotherAlias", 2, 2);
        while (aliasIterator.hasNext()) {
            ++actualAmountAliases;
            actualAlias = aliasIterator.next(); // now correct value should be saved in actualAlias
        }
        assertEquals(1, actualAmountAliases);
        assertEquals(toDelete, actualAlias);

        // now delete it
        aliasRepository.deleteByName(toDelete.getName());

        Alias actualAliasesSaved = null;
        actualAmountAliases = 0;
        while (aliasIterator.hasNext()) {
            ++actualAmountAliases;
            actualAlias = aliasIterator.next(); // now correct value should be saved in actualAlias
        }
        assertEquals(0, actualAmountAliases);
        assertNull(actualAliasesSaved);
    }

}
