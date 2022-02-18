package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.BlacklistEntry;
import de.itermori.pse.kitroomfinder.backend.repositories.BlacklistRepository;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides a service for the model {@link BlacklistEntry}.
 * Implements the service interface {@link BlacklistService} which defines
 * the corresponding GraphQL schema methods related to the model {@link BlacklistEntry}.
 * Uses the repository {@link BlacklistRepository}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Service
public class BlacklistServiceImp implements BlacklistService{

    private final BlacklistRepository blacklistRepository;

    /**
     * The constructor which initializes the alias service implementation
     * with the required repositories.
     *
     * @param blacklistRepository   The required {@link BlacklistRepository}.
     */
    @Autowired
    public BlacklistServiceImp(BlacklistRepository blacklistRepository) {
        this.blacklistRepository = blacklistRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public boolean addToBlacklist(String toBlacklist) {
        if (isBlacklisted(toBlacklist)) {
            return false;
        }
        blacklistRepository.save(new BlacklistEntry(toBlacklist));
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public boolean removeFromBlacklist(String blacklistedToRem) {
        blacklistRepository.deleteByName(blacklistedToRem);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBlacklisted(String word) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher(word, ExampleMatcher.GenericPropertyMatchers.ignoreCase());
        Example<BlacklistEntry> blacklistEntryExample = Example.of(
                new BlacklistEntry(word), matcher);
        return blacklistRepository.exists(blacklistEntryExample);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<String> getBlacklist() {
        Stream<String> stream = blacklistRepository.findAll().parallelStream()
                .map(BlacklistEntry::getName);
        return stream::iterator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAmountEntriesBlacklist() {
        return String.valueOf(blacklistRepository.count());
    }

}
