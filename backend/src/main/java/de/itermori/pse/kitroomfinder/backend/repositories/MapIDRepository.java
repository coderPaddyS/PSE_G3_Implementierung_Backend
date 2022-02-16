package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.MapID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Provides a {@link JpaRepository} interface for the model {@link MapID}.
 * Communicates with the database, more precisely with the table regarding the MapIDs.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
public interface MapIDRepository extends JpaRepository<MapID, Long> {

    /**
     * Finds the {@link MapID} stored in the database
     * which has as attribute the mapID provided.
     *
     * @param mapID The mapID which the {@link MapID} should have as attribute.
     * @return      The name of the found {@link MapID} which has as attribute
     *              the mapID provided.
     *              If no {@link MapID} is found, null is returned.
     */
    @Query("SELECT m.name FROM MapID AS m WHERE m.mapID=:mapID")
    String findByID(@Param("mapID") int mapID);

    /**
     * Finds the {@link MapID} stored in the database
     * which has as attribute the name (mapObject) provided.
     *
     * @param mapObject The name (mapObject) which the {@link MapID} should have as attribute.
     * @return          The mapID of the found {@link MapID} which has as attribute
     *                  the name (mapObject) provided.
     */
    @Query("SELECT m.mapID FROM MapID AS m WHERE m.name=:mapObject")
    int findByName(@Param("mapObject") String mapObject);

}
