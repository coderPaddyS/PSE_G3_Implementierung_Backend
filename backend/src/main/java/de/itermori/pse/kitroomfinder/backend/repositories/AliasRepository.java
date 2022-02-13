package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides a {@link JpaRepository} for the model {@link Alias}.
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
     * {@param mapID}.
     *
     * @param mapID The {@link de.itermori.pse.kitroomfinder.backend.models.MapID},
     *              whose aliases (type: {@link Alias}) are to be found and returned.
     * @return      An {@link Iterable} of all the aliases (type: {@link Alias}) that serve as an additional
     *              description for the provided {@param mapID}.
     */
    @Query("SELECT a FROM Alias AS a WHERE a.mapID=:mapID")
    Iterable<Alias> findByMapID(@Param("mapID") int mapID);

    /**
     * Finds the alias (type: {@link Alias}) that is stored in the
     * database and whose name is the provided {@param alias}.
     *
     * @param alias The name of the {@link Alias} to be found and returned.
     * @return      The alias (type: {@link Alias}) whose name is the provided {@param alias}.
     */
    @Query("SELECT a FROM Alias AS a WHERE a.name=:alias")
    Alias findByName(@Param("alias") String alias);

    /**
     * Deletes the alias (type: {@link Alias}) whose name is the provided
     * {@param name} if such an {@link Alias} is truly stored in the database.
     * Otherwise the deletion request is ignored.
     *
     * @param name  The name of the alias (type: {@link Alias}) to be deleted from the database.
     */
    @Transactional
    void deleteByName(String name);

    /**
     * Finds all aliases (type: {@link Alias}) stored in the database
     * that have a newer version than the provided {@param version}.
     *
     * @param version   The version which an alias (type: {@link Alias}) must surpass to be returned.
     * @return          An {@link Iterable} of all the aliases (type: {@link Alias}) which are stored
     *                  in the database and have a newer version than the provided {@param version}.
     */
    @Query("SELECT a FROM Alias AS a WHERE a.version>:version")
    Iterable<Alias> findUpdatesByVersion(@Param("version")int version);
    
}
