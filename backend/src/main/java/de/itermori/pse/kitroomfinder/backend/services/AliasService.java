package de.itermori.pse.kitroomfinder.backend.services;

public interface AliasService {

    public Boolean addAlias(String alias, int mapID);
    public Iterable<String> getAlias(int mapID);
    public Iterable<String> getAlias(int mapID, String user);
    public AliasUpdate getAliasUpdates(int version);
    public Boolean removeAlias(String name);
}
