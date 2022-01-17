package de.itermori.pse.kitroomfinder.backend.utilwrapper;

import de.itermori.pse.kitroomfinder.backend.models.Alias;

public class AliasUpdate {

    private int currentVersion;
    private Iterable<Alias> toDelete;
    private Iterable<Alias> toAdd;

    public AliasUpdate(int version, Iterable<Alias> toAdd, Iterable<Alias> toDelete) {
        this.toAdd = toAdd;
        this.toDelete = toDelete;
    }
}
