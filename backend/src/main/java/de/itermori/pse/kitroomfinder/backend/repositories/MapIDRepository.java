package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.MapID;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MapIDRepository {

    public int findByName(String name) {
        return 0;
    }

    public String findByID(int mapID) {
        return "test";
    }

    //deleteAfter
    public boolean save(MapID alias) {
        return true;
    }

    public Iterable<MapID> findAll() {
        return null;
    }
}
