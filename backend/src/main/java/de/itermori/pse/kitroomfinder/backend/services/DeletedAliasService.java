package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.DeletedAlias;

/**
 * Provides a service for the model {@link DeletedAlias}.
 * This service interface defines the corresponding GraphQL schema methods
 * related to the model {@link DeletedAlias}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
public interface DeletedAliasService {

    /**
     * Adds a deleted alias to the database.
     *
     * @param deletedAlias  The name of the deleted alias.
     * @param mapID         The mapID for which the deleted alias (previously) served as an additional description.
     * @return              True if the addition of the deleted alias to the database succeeds.
     */
    boolean addDeletedAlias(String deletedAlias, int mapID);

    /**
     * Returns all deleted aliases stored in the database
     * which have a newer version than the one provided.
     *
     * @param version   The version that should be older than the versions of
     *                  the to-be-returned deleted aliases.
     * @return          An {@link Iterable} of the deleted aliases stored in
     *                  the database which have a newer version than the one provided.
     */
    Iterable<DeletedAlias> getDeletedAlias(int version);

    /**
     * Removes a deleted alias entry from the database.
     *
     * @param alias The name of the deleted alias entry.
     * @param mapID The mapID for which the deleted alias (previously) served as an additional description.
     * @return      True if the removal of the deleted alias from the database succeeds
     *              or if no deleted alias with the provided name and mapID is stored in the database,
     *              otherwise false.
     */
    boolean removeDeletedAlias(String alias, int mapID);

}
