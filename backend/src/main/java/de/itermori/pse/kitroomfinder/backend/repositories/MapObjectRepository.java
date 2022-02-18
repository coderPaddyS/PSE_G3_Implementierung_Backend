package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.MapObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Provides a {@link JpaRepository} interface for the model {@link MapObject}.
 * Communicates with the database, more precisely with the table regarding the MapObjects.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
public interface MapObjectRepository extends JpaRepository<MapObject, Long> {

    /**
     * Finds the {@link MapObject} stored in the database
     * which has as attribute the mapID provided.
     *
     * @param mapID The mapID which the {@link MapObject} should have as attribute.
     * @return      The name of the found {@link MapObject} which has as attribute
     *              the mapID provided.
     *              If no {@link MapObject} is found, null is returned.
     */
    @Query("SELECT m.name FROM MapObject AS m WHERE m.mapID=:mapID")
    String findByID(@Param("mapID") int mapID);

    /**
     * Finds the {@link MapObject} stored in the database
     * which has as attribute the name (mapObject) provided.
     *
     * @param mapObjectName The name of the map object (type: {@link MapObject}).
     * @return              The mapID of the found {@link MapObject} whose name
     *                      is the name provided.
     */
    @Query("SELECT m.mapID FROM MapObject AS m WHERE m.name=:mapObjectName")
    int findByName(@Param("mapObjectName") String mapObjectName);

}
