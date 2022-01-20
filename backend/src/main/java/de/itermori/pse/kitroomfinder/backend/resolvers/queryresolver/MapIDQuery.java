package de.itermori.pse.kitroomfinder.backend.resolvers.queryresolver;

import de.itermori.pse.kitroomfinder.backend.services.MapIDService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapIDQuery implements GraphQLQueryResolver {

    private MapIDService mapIDService;

    @Autowired
    public MapIDQuery(MapIDService mapIDService) {
        this.mapIDService = mapIDService;
    }

    public String getMapObject(int mapID) {
        return mapIDService.getMapObject(mapID);
    }

    public int getMapIDByName(String mapObject) {
        return mapIDService.getMapIDByName(mapObject);
    }

    public Iterable<Integer> getAllMapIDs() {
        return mapIDService.getAllMapIDs();
    }
}
