package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.User;
import de.itermori.pse.kitroomfinder.backend.models.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides a {@link JpaRepository} interface for the model {@link Version}.
 * Communicates with the database, more precisely with the table regarding the current version.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
public interface VersionRepository extends JpaRepository<Version, Long> {

    /**
     * Increments the current version of the database.
     */
    @Modifying
    @Transactional
    @Query("UPDATE Version a SET a.currentVersion = a.currentVersion + 1")
    void incrementVersion();

    /**
     * Retrieves the current version of the database.
     *
     * @return  The current version of the database.
     */
    @Query("SELECT v.currentVersion FROM Version AS v")
    Integer retrieveCurrentVersion();

}
