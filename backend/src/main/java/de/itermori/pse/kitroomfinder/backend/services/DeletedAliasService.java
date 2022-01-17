package de.itermori.pse.kitroomfinder.backend.services;

public interface DeletedAliasService {

    public boolean addDeletedAlias(String deletedAlias, int mapID);
    public Iterable<Alias> getDeletedAlias(int version);
    public boolean removeDeletedAlias(String alias, int mapID);

}
