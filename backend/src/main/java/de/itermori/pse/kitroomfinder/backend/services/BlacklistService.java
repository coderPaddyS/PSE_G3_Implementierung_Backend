package de.itermori.pse.kitroomfinder.backend.services;

public interface BlacklistService {

    public boolean addToBlacklist(String toBlacklkist);
    public boolean removeFromBlacklist(String blacklistedToRem);
    public boolean isBlacklisted(STrign word);
    public Iterable<String> getBlacklist();

}
