package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.utilwrapper.AliasUpdate;
import org.springframework.stereotype.Repository;

@Repository
public class AliasRepository {

    public Iterable<Alias> findByMapID(int mapID) {
        return null;
    }

    public Iterable<Alias> findByMapIDAndUser(int mapID, String user) {
        return null;
    }

    public AliasUpdate findUpdatesByVersion(int version) {
        return null;
    }

    public boolean deleteByName(String alias) {
        return true;
    }

    public Alias findByName(String alias) {
        return new Alias("test", 1);
    }


    //deleteAfter
    public boolean save(Alias alias) {
        return true;
    }

    public Iterable<Alias> findAll() {
        return null;
    }

}
