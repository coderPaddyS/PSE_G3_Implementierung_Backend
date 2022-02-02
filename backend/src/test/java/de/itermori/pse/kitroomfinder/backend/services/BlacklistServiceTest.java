package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.BlacklistEntry;
import de.itermori.pse.kitroomfinder.backend.repositories.BlacklistRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BlacklistServiceTest {

    @Autowired
    private BlacklistService blacklistService;

    @Autowired
    private AliasSuggestionService aliasSuggestionService;

    @Autowired
    private BlacklistRepository blacklistRepository;

    @BeforeEach
    void setUp() {
        blacklistRepository.deleteAll();
    }

    @Test
    void whenWordNotYetAddedToBlacklist_thenAddToBlacklist() {
        // add forbidden word
        assertTrue(blacklistService.addToBlacklist("forbidden"));

        // check if forbidden word was added to blacklist
        List<BlacklistEntry> blacklistEntries = blacklistRepository.findAll();
        assertEquals(1, blacklistEntries.size());
        assertEquals("forbidden", blacklistEntries.get(0).getName());
    }

    @Test
    void whenWordAlreadyAddedToBlacklist_thenAddToBlacklist() {
        // add forbidden word twice
        assertTrue(blacklistService.addToBlacklist("forbidden"));
        assertFalse(blacklistService.addToBlacklist("forbidden"));

        // check if forbidden word was only added once to blacklist
        List<BlacklistEntry> blacklistEntries = blacklistRepository.findAll();
        assertEquals(1, blacklistEntries.size());
        assertEquals("forbidden", blacklistEntries.get(0).getName());
    }

    @Test
    void whenWordInBlacklist_thenRemoveFromBlacklist() {
        // add forbidden word to blacklist
        BlacklistEntry blacklistEntryToRemove = new BlacklistEntry("forbidden");
        blacklistRepository.save(blacklistEntryToRemove);

        // remove forbidden word from blacklist
        assertTrue(blacklistService.removeFromBlacklist("forbidden"));

        // check if forbidden word is no longer in blacklist
        List<BlacklistEntry> blacklistEntries = blacklistRepository.findAll();
        assertEquals(0, blacklistEntries.size());
    }

    @Test
    void whenWordInBlacklist_thenIsBlacklisted() {
        // add forbidden word to blacklist
        BlacklistEntry blacklistEntry = new BlacklistEntry("forbidden");
        blacklistRepository.save(blacklistEntry);

        assertTrue(blacklistService.isBlacklisted("forbidden"));
    }
}
