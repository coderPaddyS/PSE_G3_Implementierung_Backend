package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.Alias;

public interface AliasService {

    public boolean addAlias(String alias, int mapID, String mapObject);
    public Iterable<Alias> getAlias(int mapID);
    public Iterable<Alias> getAliasUpdates(int version);
    public boolean removeAlias(String name);
}
