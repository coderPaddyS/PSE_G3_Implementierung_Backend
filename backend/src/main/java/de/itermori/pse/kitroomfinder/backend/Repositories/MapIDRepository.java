package de.itermori.pse.kitroomfinder.backend.Repositories;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import de.itermori.pse.kitroomfinder.backend.models.MapID;
import org.springframework.stereotype.Repository;

@Repository
public class MapIDRepository {

    public int findByName(String name) {
        return 0;
    }

    public String findByID(int mapID) {
        return "test";
    }

    //deleteAfter
    public boolean safe(MapID alias) {
        return true;
    }

    public Iterable<MapID> findAll() {
        return null;
    }
}
