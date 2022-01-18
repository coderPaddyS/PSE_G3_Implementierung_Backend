package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.repositories.BlacklistRepository;
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
        return blacklistRepository.save(toBlacklist);
    }

    @Override
    public boolean removeFromBlacklist(String blacklistedToRem) {
        return blacklistRepository.deleteByName(blacklistedToRem);
    }

    @Override
    public boolean isBlacklisted(String word) {
        if (blacklistRepository.find(word).equals(null)) {
            return false;
        }
        return true;
    }

    @Override
    public Iterable<String> getBlacklist() {
        return blacklistRepository.findAll();
    }
}
