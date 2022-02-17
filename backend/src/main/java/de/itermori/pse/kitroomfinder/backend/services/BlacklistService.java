package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.BlacklistEntry;

/**
 * Provides a service for the model {@link BlacklistEntry}.
 * This service interface defines the corresponding GraphQL schema methods
 * related to the model {@link BlacklistEntry}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
public interface BlacklistService {

    /**
     * Adds a blacklist entry to the database
     * corresponding to the provided {@link String}.
     *
     * @param toBlacklist   The String that should be blacklisted.
     * @return              True if the addition of the blacklist entry succeeds
     *                      or if the blacklist entry was already stored in the database,
     *                      otherwise false.
     */
    boolean addToBlacklist(String toBlacklist);

    /**
     * Removes the desired blacklist entry from the database
     * whose {@link String} representation (name) corresponds
     * to the one provided.
     *
     * @param blacklistedToRem  The String which the name of the to-be-removed blacklist entry should have.
     * @return                  True if the removal of the blacklist entry succeeds
     *                          or if no blacklist entry with the provided name exists in the database,
     *                          otherwise false.
     */
    boolean removeFromBlacklist(String blacklistedToRem);

    /**
     * Returns a boolean which indicates
     * if a {@link String} is blacklisted or not,
     * that is if it is stored in the database or not.
     * Case sensitiveness is ignored.
     *
     * @param word  The {@link String} that should be verified whether it is blacklisted or not.
     * @return      True if the {@link String} is blacklisted or not.
     */
    boolean isBlacklisted(String word);

    /**
     * Returns all blacklisted entries stored in the database.
     *
     * @return  An {@link Iterable} which contains all blacklisted entries
     *          stored in the database.
     */
    Iterable<String> getBlacklist();

    /**
     * Returns the amount of blacklist entries stored in the database.
     *
     * @return  The amount of blacklist entries stored in the database, converted to a {@link String}.
     *          A {@link String} is returned since GraphQL does not (currently) support
     *          a long datatype.
     */
    String getAmountEntriesBlacklist();

}
