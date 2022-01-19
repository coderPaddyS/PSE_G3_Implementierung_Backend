package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.BlacklistEntry;
import de.itermori.pse.kitroomfinder.backend.repositories.BlacklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlacklistServiceImp implements BlacklistService{

    private BlacklistRepository blacklistRepository;

    @Autowired
    public BlacklistServiceImp(BlacklistRepository blacklistRepository) {
        this.blacklistRepository = blacklistRepository;
    }

    @Transactional
    @Override
    public boolean addToBlacklist(String toBlacklist) {
        if (blacklistRepository.save(new BlacklistEntry(toBlacklist)) == null) {
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public boolean removeFromBlacklist(String blacklistedToRem) {
        return blacklistRepository.deleteByName(blacklistedToRem);
    }

    @Override
    public boolean isBlacklisted(String word) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher(word, ExampleMatcher.GenericPropertyMatchers.ignoreCase());
        Example<BlacklistEntry> blacklistEntryExample = Example.of(
                new BlacklistEntry(word), matcher);
        if (blacklistRepository.exists(blacklistEntryExample)) {
            return false;
        }
        return true;
    }

    @Override
    public Iterable<String> getBlacklist() {
        return blacklistRepository.findAll();
    }
}
