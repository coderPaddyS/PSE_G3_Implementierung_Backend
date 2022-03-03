package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.models.MapObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides a {@link JpaRepository} interface for the model {@link Alias}.
 * Communicates with the database, more precisely with the table regarding the aliases.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
public interface AliasRepository extends JpaRepository<Alias, Long> {

    /**
     * Finds all aliases (type: {@link Alias}) that are stored in the database and
     * that serve as an additional description for the provided
     * mapID.
     *
     * @param mapID The {@link MapObject},
     *              whose aliases (type: {@link Alias}) are to be found and returned.
     * @return      An {@link Iterable} of all the aliases (type: {@link Alias}) that serve as an additional
     *              description for the provided mapID.
     */
    @Query("SELECT a FROM Alias AS a WHERE a.mapID=:mapID")
    Iterable<Alias> findByMapID(@Param("mapID") int mapID);

    /**
     * Finds all aliases (type: {@link Alias}) that are stored in the database and
     * whose names correspond to the provided name.
     *
     * @param name The name which the aliases should have.
     * @return      An {@link Iterable} of all the aliases (type: {@link Alias}) whose
     *              names correspond to the provided name.
     */
    @Query("SELECT a FROM Alias AS a WHERE a.name=:name")
    Iterable<Alias> findByName(@Param("name") String name);

    /**
     * Finds the alias (type: {@link Alias}) that is stored in the
     * database and whose name is the provided alias and which
     * serves as an additional description for the provided mapID.
     *
     * @param name  The name of the alias to be found and returned.
     * @param mapID The mapID for which the alias should serve as an additional description.
     * @return      The alias (type: {@link Alias}) whose name is the provided alias and which
     *              serve as an additional description for the provided name.
     */
    @Query("SELECT a FROM Alias AS a WHERE a.name=:name AND a.mapID=:mapID")
    Alias findByNameAndMapID(@Param("name") String name, @Param("mapID") int mapID);

    /**
     * Deletes the alias (type: {@link Alias}) whose name is the provided
     * name if such an {@link Alias} is truly stored in the database.
     * Otherwise the deletion request is ignored.
     *
     * @param name  The name of the alias (type: {@link Alias}) to be deleted from the database.
     * @param mapID The mapID for which the alias serves as an additional description.
     */
    @Transactional
    void deleteByName(String name, int mapID);

    /**
     * Finds all aliases (type: {@link Alias}) stored in the database
     * that have a newer version than the provided version.
     *
     * @param version   The version which an alias (type: {@link Alias}) must surpass to be returned.
     * @return          An {@link Iterable} of all the aliases (type: {@link Alias}) which are stored
     *                  in the database and have a newer version than the provided version.
     */
    @Query("SELECT a FROM Alias AS a WHERE a.version>:version")
    Iterable<Alias> findUpdatesByVersion(@Param("version")int version);
    
}
