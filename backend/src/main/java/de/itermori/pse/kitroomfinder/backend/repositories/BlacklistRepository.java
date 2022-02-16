package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import de.itermori.pse.kitroomfinder.backend.models.BlacklistEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides a {@link JpaRepository} interface for the model {@link BlacklistEntry}.
 * Communicates with the database, more precisely with the table regarding the blacklist entries.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
public interface BlacklistRepository extends JpaRepository<BlacklistEntry, Long> {

    /**
     * Deletes the blacklist entry (type: {@link BlacklistEntry} from the database
     * whose name corresponds to the provided name.
     *
     * @param name  The name that the to-be-deleted blacklist entry should have.
     */
    @Transactional
    void deleteByName(String name);

}
