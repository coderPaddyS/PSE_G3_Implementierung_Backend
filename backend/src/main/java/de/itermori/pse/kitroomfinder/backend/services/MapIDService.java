package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.MapID;

/**
 * Provides a service for the model {@link MapIDService}.
 * This service interface defines the corresponding GraphQL schema methods
 * related to the model {@link MapIDService}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
public interface MapIDService {

    /**
     * Returns the name (mapObject) of
     * the desired {@link MapID} stored in the database.
     *
     * @param mapID The mapID of the desired {@link MapID} stored in the database.
     * @return      The name (mapObject) of the desired {@link MapID} stored in the database.
     */
    String getMapObject(int mapID);

    /**
     * Returns the mapID of
     * the desired {@link MapID} stored in the database.
     *
     * @param mapObject The name (mapObject) of the desired {@link MapID} stored in the database.
     * @return          The mapID of the desired {@link MapID} stored in the database.
     */
    int getMapIDByName(String mapObject);

    /**
     * Returns the mapIDs of all MapIDs (type: {@link MapID}) stored in the database.
     *
     * @return  An {@link Iterable} of all the mapIDs of all MapIDs (type: {@link MapID}) stored in the database.
     */
    Iterable<Integer> getAllMapIDs();

    /**
     * Returns the names (mapObjects) of all MapIDs (type: {@link MapID} stored in the database.
     *
     * @return  An {@link Iterable} of all the names (mapObjects)
     *          of all MapIDs (type: {@link MapID}) stored in the database.
     */
    Iterable<String> getAllMapIDsName();

}
