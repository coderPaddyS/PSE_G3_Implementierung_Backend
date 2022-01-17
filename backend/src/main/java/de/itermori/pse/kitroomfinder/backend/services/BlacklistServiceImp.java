package de.itermori.pse.kitroomfinder.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlacklistServiceImp implements BlacklistService{

    private BlacklistRepository blacklistRepository;

    @Autowired
    public BlacklistServiceImp(BlacklistRepository blacklistRepository) {
        this.blacklistRepository = blacklistRepository;
    }

    @Override
    public boolean addToBlacklist(String toBlacklist) {
        return blacklistRepository.safe(toBlacklist);
    }

    @Override
    public boolean removeFromBlacklist(String blacklistedToRem) {
        return removeFromBlacklist(blacklistedToRem);
    }

    @Override
    public boolean isBlacklisted(String word) {
        return isBlacklisted(word);
    }

    @Override
    public Iterable<String> getBlacklist() {
        return getBlacklist();
    }
}
