package de.itermori.pse.kitroomfinder.backend.Resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class mapIDQuery implements GraphQLQueryResolver{

    private MapIDServie mapIDService;

    @Autowired
    public mapIDQuery(MapIDService  mapIDService) {
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
