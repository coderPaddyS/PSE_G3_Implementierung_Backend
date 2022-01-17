package de.itermori.pse.kitroomfinder.backend.services;

public interface BlacklistService {

    public Boolean addToBlacklist(String toBlacklkist);
    public Boolean removeFromBlacklist(String blacklistedToRem);
    public Boolean isBlacklisted(STrign word);
    public Iterable<String> getBlacklist();

}
