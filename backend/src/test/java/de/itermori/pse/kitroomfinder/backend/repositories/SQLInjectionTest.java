package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test class for {@link AliasRepository}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SQLInjectionTest {

    @Autowired
    private AliasRepository aliasRepository;

    @Autowired
    private AliasSuggestionRepository aliasSuggestionRepository;

    /**
     * Sets up the test resources.
     */
    @BeforeEach
    void setUp() {
        aliasRepository.deleteAll();
        aliasSuggestionRepository.deleteAll();
    }

    /**
     * Tests if {@link AliasRepository#findByName(String)}
     * (and with it indirectly all self implemented queries and mutations)
     * is prone to SQL Injection attacks.
     */
    @Test
    void whenSQLInjection_thenFindByName() {
        Alias alias1 = new Alias("alias1", 1, "50.34", 1);
        Alias alias2 = new Alias("alias2", 2, "50.34", 1);
        aliasRepository.save(alias1);
        aliasRepository.save(alias2);
        Iterable<Alias> aliasIterable = aliasRepository.findByName("alias1 'OR '1'='1");
        Iterator<Alias> aliasIterator = aliasIterable.iterator();
        Alias alias = null;
        while (aliasIterator.hasNext()) {
            alias = aliasIterator.next();
        }
        assertNull(alias);
    }

    /**
     * Tests if {@link AliasSuggestionRepository#findAmount(int, int, String)}
     * (the only native query in the project) is prone to SQL Injection attacks.
     */
    @Test
    void whenSQLInjection_thenFindAmount() {
        AliasSuggestion suggestion = new AliasSuggestion("suggestion", 1, "50.34", "'OR '1'='1");
        aliasSuggestionRepository.save(suggestion);
        Iterable<AliasSuggestion> iterable = aliasSuggestionRepository.findAmount(1, 1, "'OR '1'='1");
        Iterator<AliasSuggestion> iterator = iterable.iterator();
        AliasSuggestion aliasSuggestion = null;
        while (iterator.hasNext()) {
            aliasSuggestion = iterator.next();
        }
        assertNull(aliasSuggestion);
    }
    
}
