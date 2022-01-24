package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.models.DeletedAlias;

public interface DeletedAliasService {

    public boolean addDeletedAlias(String deletedAlias, int mapID);
    public Iterable<DeletedAlias> getDeletedAlias(int version);
    public boolean removeDeletedAlias(String alias, int mapID);

}
