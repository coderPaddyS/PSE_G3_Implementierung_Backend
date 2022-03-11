package de.itermori.pse.kitroomfinder.backend.resolvers.queryresolver;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import de.itermori.pse.kitroomfinder.backend.services.AliasSuggestionService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

/**
 * Provides a {@link GraphQLQueryResolver} for the model {@link AliasSuggestion}.
 * Uses the service {@link AliasSuggestionService}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Component
public class AliasSuggestionQuery implements GraphQLQueryResolver {

    private final AliasSuggestionService aliasSuggestionService;

    /**
     * Constructor: Demands for the initialization an {@link AliasSuggestionService}.
     *
     * @param aliasSuggestionService    The required {@link AliasSuggestionService}.
     */
    @Autowired
    public AliasSuggestionQuery(AliasSuggestionService aliasSuggestionService) {
        this.aliasSuggestionService = aliasSuggestionService;
    }

    /**
     * Returns all alias suggestions that have a provided minimum of upvotes
     * and a provided minimum of downvotes.
     * The caller has to be an admin.
     *
     * @param minValToShowPos   The minimum amount of upvotes the alias suggestions must have.
     * @param minValToShowNeg   The minimum amount of downvotes the alias suggestions must have.
     * @return                  An {@link Iterable} of all alias suggestions have a provided minimum of upvotes
     *                          and a provided minimum of downvotes.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<AliasSuggestion> getAliasSuggestions(int minValToShowPos, int minValToShowNeg) {
        return aliasSuggestionService.getAliasSuggestions(minValToShowPos,minValToShowNeg);
    }

    /**
     * Returns alias suggestions
     * which serve as an additional description of the provided
     * mapID and which were suggested by the provided user.
     * The provided amount caps the maximum of alias suggestions
     * that should be returned.
     * The caller has to be a user.
     *
     * @param mapID     The mapID for which the alias suggestion serves as an additional description.
     * @param amount    The maximum amount of alias suggestions that should be returned.
     * @param user      The user who suggested the alias suggestions.
     * @return          An {@link Iterable} of alias suggestions
     *                  which serve as an additional description for the provided mapID
     *                  and which were suggested by the provided user, whereby the amount of alias suggestions
     *                  returned is limited by the provided amount.
     */
    @PreAuthorize("hasAuthority('USER')")
    public Iterable<AliasSuggestion> getAliasSuggestionsAmount(int mapID, int amount, String user) {
        return aliasSuggestionService.getAliasSuggestionsAmount(mapID, amount, user);
    }

    /**
     * Returns the amount of upvotes the alias suggestion has.
     * The alias suggestion is the one which corresponds to the name provided
     * and which serves as an additional description for the provided mapID.
     * The caller has to be an admin.
     *
     * @param aliasSuggestion   The name of the alias suggestion.
     * @param mapID             The mapID for which the alias suggestion serves as an additional description.
     * @return                  The amount of upvotes the alias suggestion has.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public Integer getPosVotes(String aliasSuggestion, int mapID) {
        return aliasSuggestionService.getPosVotes(aliasSuggestion, mapID);
    }

    /**
     * Returns the amount of downvotes the alias suggestion has.
     * The alias suggestion is the one which corresponds to the name provided
     * and which serves as an additional description for the provided mapID.
     * The caller has to be an admin.
     *
     * @param aliasSuggestion   The name of the alias suggestion.
     * @param mapID             The mapID for which the alias suggestion serves as an additional description.
     * @return                  The amount of downvotes the alias suggestion has.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public Integer getNegVotes(String aliasSuggestion, int mapID) {
        return aliasSuggestionService.getNegVotes(aliasSuggestion, mapID);
    }

    /**
     * Returns the amount of alias suggestions.
     * The caller has to be an admin.
     *
     * @return  The amount of alias suggestions, converted to a {@link String}.
     *          A {@link String} is returned since GraphQL does not (currently) support
     *          a long datatype.
     */

    public String getAmountEntriesAliasSuggestion() {
        return aliasSuggestionService.getAmountEntriesAliasSuggestion();
    }

}
