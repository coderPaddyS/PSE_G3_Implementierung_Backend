package de.itermori.pse.kitroomfinder.backend.resolvers.queryresolver;

import de.itermori.pse.kitroomfinder.backend.services.MapIDService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class MapIDQuery implements GraphQLQueryResolver {

    private MapIDService mapIDService;

    @Autowired
    public MapIDQuery(MapIDService mapIDService) {
        this.mapIDService = mapIDService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public String getMapObject(int mapID) {
        return mapIDService.getMapObject(mapID);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public int getMapIDByName(String mapObject) {
        return mapIDService.getMapIDByName(mapObject);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<Integer> getAllMapIDs() {
        return mapIDService.getAllMapIDs();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<String> getAllMapIDsName() {
        return mapIDService.getAllMapIDsName();
    }

}
