package de.itermori.pse.kitroomfinder.backend.services;

public interface BlacklistService {

    public boolean addToBlacklist(String toBlacklist);
    public boolean removeFromBlacklist(String blacklistedToRem);
    public boolean isBlacklisted(String word);
    public Iterable<String> getBlacklist();
    public String getAmountEntriesBlacklist();

}
