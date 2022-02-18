package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.User;

/**
 * Provides a service for the model {@link User}.
 * This service interface defines the corresponding GraphQL schema methods
 * related to the model {@link User}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
public interface UserService {

    /**
     * Adds a user to the database.
     *
     * @param accessToken   The access token of the user that is to be added.
     * @return              The unique username of the added user.
     */
    String addUser(String accessToken);

    /**
     * Returns the {@link User} whose
     * access token corresponds to the one provided.
     *
     * @param accessToken   The access token of the user to be returned.
     * @return              The {@link User} whose access token corresponds to the one provided.
     */
    User loadUserByToken(String accessToken);

    /**
     * Returns a boolean that indicates if the caller of this method
     * is an admin or not.
     *
     * @return  True if the caller of this method is an admin,
     *          otherwise an {@link org.springframework.security.access.AccessDeniedException}
     *          is thrown.
     */
    Boolean isAdmin();

}
