package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.DeletedAlias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides a {@link JpaRepository} interface for the model {@link DeletedAlias}.
 * Communicates with the database, more precisely with the table regarding the deleted aliases.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
public interface DeletedAliasRepository extends JpaRepository<DeletedAlias, Long> {

    /**
     * Finds all deleted aliases (type: {@link DeletedAlias}) saved in the database
     * that have a newer version than the one provided,
     * and returns them in an {@link Iterable}.
     *
     * @param version   The version which has to be older than the found and returned deleted aliases.
     * @return          An {@link Iterable} of all the deleted aliases (type: {@link DeletedAlias})
     *                  found stored in the database that have a newer version than the one provided.
     */
    @Query("SELECT a FROM DeletedAlias AS a WHERE a.version>=:version")
    Iterable<DeletedAlias> findNewerThanVersion(@Param("version")int version);

    /**
     * Deletes the deleted alias (type: {@link DeletedAlias}) from the database
     * whose name corresponds to the name provided and whose assigned mapID corresponds
     * to the mapID provided.
     *
     * @param name      The name which the deleted alias should have to be removed from the database.
     * @param mapID     The mapID which the deleted alias should have to be removed from the database.
     */
    @Transactional
    void deleteByNameAndMapID(String name, int mapID);
    
}
