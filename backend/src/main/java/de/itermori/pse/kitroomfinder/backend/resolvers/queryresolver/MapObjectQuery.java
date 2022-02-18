package de.itermori.pse.kitroomfinder.backend.resolvers.queryresolver;

import de.itermori.pse.kitroomfinder.backend.models.MapObject;
import de.itermori.pse.kitroomfinder.backend.services.MapObjectService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

/**
 * Provides a {@link GraphQLQueryResolver} for the model {@link MapObject}.
 * Uses the service {@link MapObjectService}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Component
public class MapObjectQuery implements GraphQLQueryResolver {

    private final MapObjectService mapObjectService;

    /**
     * Constructor: Demands for the initialization a {@link MapObjectService}.
     *
     * @param mapObjectService          The required {@link MapObjectService}.
     */
    @Autowired
    public MapObjectQuery(MapObjectService mapObjectService) {
        this.mapObjectService = mapObjectService;
    }

    /**
     * Returns the map object name of the provided mapID.
     * The caller has to be an admin.
     *
     * @param mapID The mapID.
     * @return      The map object name of the provided mapID.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getMapObjectName(int mapID) {
        return mapObjectService.getMapObjectName(mapID);
    }

    /**
     * Returns the mapID of the provided map object name.
     * The caller has to be an admin.
     *
     * @param mapObjectName The map object name.
     * @return              The mapID of the provided map object name.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public Integer getMapIDByName(String mapObjectName) {
        return mapObjectService.getMapIDByName(mapObjectName);
    }

    /**
     * Returns all mapIDs.
     * The caller has to be an admin.
     *
     * @return  An {@link Iterable} of all mapIDs.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<Integer> getAllMapIDs() {
        return mapObjectService.getAllMapIDs();
    }

    /**
     * Returns all map object names.
     * The caller has to be an admin.
     *
     * @return  An {@link Iterable} of all map object names.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<String> getAllMapObjectsName() {
        return mapObjectService.getAllMapObjectsName();
    }

}
