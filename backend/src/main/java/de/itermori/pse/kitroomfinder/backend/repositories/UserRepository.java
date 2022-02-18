package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Provides a {@link JpaRepository} interface for the model {@link User}.
 * Communicates with the database, more precisely with the table regarding the users.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds the user (type: {@link User}) from the database
     * whose name corresponds to the name (parameter user) provided.
     *
     * @param user  The name of the user (type: {@link User})
     *              to be found in the database.
     * @return      The found user from the database whose name
     *              corresponds to the name (parameter user) provided.
     */
    @Query("SELECT u FROM User AS u WHERE u.name=:user")
    User findByName(@Param("user") String user);

}
