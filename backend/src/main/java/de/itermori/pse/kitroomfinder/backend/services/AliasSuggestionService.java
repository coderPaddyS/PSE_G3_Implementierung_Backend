package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.exceptions.NoSuchAliasSuggestionException;
import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;

/**
 * Provides a service for the model {@link AliasSuggestion}.
 * This service interface defines the corresponding GraphQL schema methods
 * related to the model {@link AliasSuggestion}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
public interface AliasSuggestionService {

    /**
     * Adds an alias suggestion (type: {@link AliasSuggestion}) to the database.
     *
     * @param aliasSuggestion   The name of the alias suggestion.
     * @param mapID             The mapID for which the alias suggestion should serve as an additional description.
     * @param mapObject         The name of the mapID.
     * @param user              The user who suggests the alias.
     * @return                  True when the addition of the alias suggestion to the database succeeds,
     *                          otherwise false (e.g. when the alias suggestion is blacklisted
     *                          or when it is already stored in the database).
     */
    boolean addAliasSuggestion(String aliasSuggestion, int mapID, String mapObject, String user);

    /**
     * Removes the alias suggestion from the database
     * whose name corresponds to the name provided and
     * which serves as an additional description
     * for the provided mapID.
     *
     * @param aliasSuggestion   The name of the alias suggestion which shall be removed from the database.
     * @param mapID             The mapID for which the alias suggestion serves as an additional description.
     * @return                  True when the removal of the alias suggestion from the database succeeds,
     *                          or when no alias suggestion with the provided name and mapID is stored in the database,
     *                          otherwise false.
     */
    boolean removeAliasSuggestion(String aliasSuggestion, int mapID);

    /**
     * Removes all alias suggestions from the database
     * whose names correspond to the name provided.
     *
     * @param aliasSuggestion   The name of the alias suggestions which shall be removed from the database.
     * @return                  True when the removal of all alias suggestions from the database succeeds,
     *                          or when no alias suggestion with the provided name is stored in the database,
     *                          otherwise false.
     */
    boolean removeAliasSuggestion(String aliasSuggestion);

    /**
     * Votes for an alias suggestion already stored in the database.
     * Users are only able to vote once.
     * The user who suggested the alias is not able to vote.
     *
     * @param aliasSuggestion   The name of the alias suggestion.
     * @param mapID             The mapID for which the alias suggestion serves as an additional description.
     * @param user              The user who votes for the alias suggestion.
     * @param vote              True if the user upvotes the alias suggestion, false if the user downvotes it.
     * @return                  True if the evaluation of the alias suggestion succeeds, otherwise false.
     */
    boolean voteForAlias(String aliasSuggestion, int mapID, String user, boolean vote);

    /**
     * Returns all alias suggestions stored in the database
     * which have the provided minimum amount of upvotes
     * and the provided minimum amount of downvotes.
     *
     * @param minValToShowPos   The minimum amount of upvotes the alias suggestions must have to be returned.
     * @param minValToShowNeg   The minimum amount of downvotes the alias suggestions must have to be returned.
     * @return                  An {@link Iterable} of the alias suggestions stored in the database which have
     *                          the provided minimum amount of upvotes and the provided minimum amount of downvotes.
     */
    Iterable<AliasSuggestion> getAliasSuggestions(int minValToShowPos, int minValToShowNeg);

    /**
     * Returns alias suggestions stored in the database
     * which serve as an additional description of the provided
     * mapID and which were suggested by the provided user.
     * The provided amount caps the maximum of alias suggestions
     * that should be returned.
     *
     * @param mapID     The mapID for which the alias suggestion serves as an additional description.
     * @param amount    The maximum amount of alias suggestions that should be returned.
     * @param user      The user who suggested the alias suggestions.
     * @return          An {@link Iterable} of alias suggestions stored in the database
     *                  which serve as an additional description for the provided mapID
     *                  and which were suggested by the provided user, whereby the amount of alias suggestions
     *                  returned is limited by the provided amount.
     */
    Iterable<AliasSuggestion> getAliasSuggestionsAmount(int mapID, int amount, String user);

    /**
     * Returns the amount of alias suggestions stored in the database.
     *
     * @return  The amount of alias suggestions stored in the database, converted to a {@link String}.
     *          A {@link String} is returned since GraphQL does not (currently) support
     *          a long datatype.
     */
    String getAmountEntriesAliasSuggestion();

    /**
     * Returns the amount of upvotes the
     * alias suggestion stored in the database has.
     * The alias suggestion (type: {@link AliasSuggestion})
     * is the one which corresponds to the name provided
     * and which serves as an additional description for the provided mapID.
     *
     * @param aliasSuggestion   The name of the alias suggestion.
     * @param mapID             The mapID for which the alias suggestion serves as an additional description.
     * @return                  The amount of upvotes the alias suggestion stored in the database has.
     */
    int getPosVotes(String aliasSuggestion, int mapID);

    /**
     * Returns the amount of downvotes the
     * alias suggestion stored in the database has.
     * The alias suggestion (type: {@link AliasSuggestion})
     * is the one which corresponds to the name provided
     * and which serves as an additional description for the provided mapID.
     *
     * @param aliasSuggestion   The name of the alias suggestion.
     * @param mapID             The mapID for which the alias suggestion serves as an additional description.
     * @return                  The amount of downvotes the alias suggestion stored in the database has.
     */
    int getNegVotes(String aliasSuggestion, int mapID);

}
