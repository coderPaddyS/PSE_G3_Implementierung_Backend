package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.BlacklistEntry;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BlacklistRepositoryTest {

    @Autowired
    private BlacklistRepository blacklistRepository;

    @BeforeEach
    void setUp() {
        blacklistRepository.deleteAll();
    }

    @Test
    void whenBlacklisted_thenDeleteByName() {
        // save a BlacklistEntry to the database
        BlacklistEntry blacklistEntry = new BlacklistEntry("forbidden");
        blacklistRepository.save(blacklistEntry);

        // check if blacklistEntry is saved in the database
        List<BlacklistEntry> blacklistEntries = blacklistRepository.findAll();
        assertEquals(1, blacklistEntries.size());
        assertEquals(blacklistEntry, blacklistEntries.get(0));

        // delete blacklistEntry
        blacklistRepository.deleteByName(blacklistEntry.getName());

        // check if blacklistEntry is no longer saved in the database
        blacklistEntries = blacklistRepository.findAll();
        assertTrue(blacklistEntries.isEmpty());
    }
    
}
