package de.itermori.pse.kitroomfinder.backend.services;

public interface DeletedAliasService {

    public Boolean addDeletedAlias(String deletedAlias, int mapID);
    public Iterable<Alias> getDeletedAlias(int version);
    public Boolean removeDeletedAlias(String alias, int mapID);

}
