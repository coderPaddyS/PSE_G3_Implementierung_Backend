package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import org.springframework.stereotype.Repository;

@Repository
public class AliasRepository {

    public Iterable<String> findByMapID(int mapID) {
        return null;
    }

    public Iterable<String> findByMapIDAndUser(int mapID, String user) {
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
