package de.itermori.pse.kitroomfinder.backend.resolvers.queryresolver;

import de.itermori.pse.kitroomfinder.backend.services.MapObjectService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class MapObjectQuery implements GraphQLQueryResolver {

    private MapObjectService mapObjectService;

    @Autowired
    public MapObjectQuery(MapObjectService mapObjectService) {
        this.mapObjectService = mapObjectService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public String getMapObjectName(int mapID) {
        return mapObjectService.getMapObjectName(mapID);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Integer getMapIDByName(String mapObject) {
        return mapObjectService.getMapIDByName(mapObject);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<Integer> getAllMapIDs() {
        return mapObjectService.getAllMapIDs();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<String> getAllMapObjectsName() {
        return mapObjectService.getAllMapObjectsName();
    }

}
