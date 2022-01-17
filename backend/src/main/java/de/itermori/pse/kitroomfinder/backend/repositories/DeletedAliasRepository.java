package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeletedAliasRepository {

    public List<Alias> findNewerThanVersion(int version) {
        return null;
    }

    public boolean deleteByNameAndMapID(String name, int mapID) {
        return true;
    }

    //deleteAfter
    public boolean save(Alias alias) {
        return true;
    }

    public Iterable<Alias> findAll() {
        return null;
    }

}
