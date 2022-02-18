package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.MapObject;

/**
 * Provides a service for the model {@link MapObject}.
 * This service interface defines the corresponding GraphQL schema methods
 * related to the model {@link MapObject}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
public interface MapObjectService {

    /**
     * Returns the name of
     * the desired {@link MapObject} stored in the database.
     *
     * @param mapID The mapID of the desired {@link MapObject} stored in the database.
     * @return      The name (mapObject) of the desired {@link MapObject} stored in the database.
     */
    String getMapObjectName(int mapID);

    /**
     * Returns the mapID of
     * the desired {@link MapObject} stored in the database.
     *
     * @param name      The name of the desired {@link MapObject} stored in the database.
     * @return          The mapID of the desired {@link MapObject} stored in the database.
     */
    Integer getMapIDByName(String name);

    /**
     * Returns the mapIDs of all MapObjects (type: {@link MapObject}) stored in the database.
     *
     * @return  An {@link Iterable} of all the mapIDs of all MapObjects
     *          (type: {@link MapObject}) stored in the database.
     */
    Iterable<Integer> getAllMapIDs();

    /**
     * Returns the names of all MapObjects (type: {@link MapObject}) stored in the database.
     *
     * @return  An {@link Iterable} of all the names
     *          of all MapObjects (type: {@link MapObject}) stored in the database.
     */
    Iterable<String> getAllMapObjectsName();

}
