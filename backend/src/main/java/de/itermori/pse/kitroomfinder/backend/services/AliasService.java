package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.utilwrapper.AliasUpdate;

public interface AliasService {

    public boolean addAlias(String alias, int mapID);
    public Iterable<Alias> getAlias(int mapID);
    public Iterable<Alias> getAlias(int mapID, String user);
    public Iterable<Alias> getAliasUpdates(int version);
    public boolean removeAlias(String name);
}
