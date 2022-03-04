package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.Alias;

/**
 * Provides a service for the model {@link Alias}.
 * This service interface defines the corresponding GraphQL schema methods
 * related to the model {@link Alias}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
public interface AliasService {

    /**
     * Adds an alias (type: {@link Alias}) to the database.
     *
     * @param alias     The name of the alias.
     * @param mapID     The mapID for which the alias should serve as an additional description.
     *
     * @return          True when the addition of the alias to the database succeeds, otherwise false
     *                  (e.g. when the alias is already stored in the database).
     */
    boolean addAlias(String alias, int mapID);

    /**
     * Returns all aliases (type: {@link Alias}) stored in the database
     * which serve as an additional description for the provided mapID.
     *
     * @param mapID The mapID for which the aliases should serve as an additional description.
     * @return      An {@link Iterable} which contains all aliases stored in the database
     *              which serve as an additional description for the provided mapID.
     */
    Iterable<Alias> getAlias(int mapID);

    /**
     * Returns all aliases (type: {@link Alias}) stored in the database
     * which have the provided name.
     *
     * @param name  The name which the aliases should have.
     * @return      An {@link Iterable} which contains all aliases stored in the database
     *              which have the provided name.
     */
    Iterable<Alias> getAliasByName(String name);

    /**
     * Returns all aliases (type: {@link Alias}) stored in the database.
     *
     * @return      An {@link Iterable} which contains all the aliases stored in the database.
     */
    Iterable<Alias> getAllAliases();

    /**
     * Returns all aliases (type: {@link Alias}) stored in the database
     * which have a newer version than the provided one, that is
     * all the aliases which were added to the database after
     * the provided version.
     *
     * @param version   The version that should be older than the versions of
     *                  the to-be-returned aliases.
     * @return          The aliases which have a newer version than the provided one.
     */
    Iterable<Alias> getAliasUpdates(int version);

    /**
     * Returns the amount of aliases stored in the database.
     *
     * @return  The amount of aliases stored in the database, converted to a {@link String}.
     *          A {@link String} is returned since GraphQL does not (currently) support
     *          a long datatype.
     */
    String getAmountEntriesAlias();

    /**
     * Removes the alias stored from the database
     * whose name corresponds to the provided one
     * and which serves as an additional description
     * for the provided mapID.
     * Adds the alias to the deleted alias table of the database.
     *
     * @param name  The name of the alias that should be removed from the database.
     * @param mapID The mapID for which the alias serves as an additional description.
     * @return      True if the alias is deleted successfully from the database
     *              or if no alias with the provided name is stored in the database,
     *              otherwise false.
     */
    boolean removeAlias(String name, int mapID);

}
