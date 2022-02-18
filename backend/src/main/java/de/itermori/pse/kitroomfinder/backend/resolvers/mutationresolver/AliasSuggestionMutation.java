package de.itermori.pse.kitroomfinder.backend.resolvers.mutationresolver;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import de.itermori.pse.kitroomfinder.backend.services.AliasService;
import de.itermori.pse.kitroomfinder.backend.services.AliasSuggestionService;
import de.itermori.pse.kitroomfinder.backend.services.BlacklistService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

/**
 * Provides a {@link GraphQLMutationResolver} for the model {@link AliasSuggestion}.
 * Uses the services {@link AliasService}, {@link AliasSuggestionService},
 * {@link BlacklistService}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Component
public class AliasSuggestionMutation implements GraphQLMutationResolver {

    private final AliasService aliasService;
    private final AliasSuggestionService aliasSuggestionService;
    private final BlacklistService blacklistService;

    /**
     * Constructor: Demands for the initialization of the class {@link AliasService}, {@link AliasSuggestionService},
     * {@link BlacklistService}.
     *
     * @param aliasService              The required {@link AliasService}.
     * @param aliasSuggestionService    The required {@link AliasSuggestionService}.
     * @param blacklistService          The required {@link BlacklistService}.
     */
    @Autowired
    public AliasSuggestionMutation(AliasService aliasService, AliasSuggestionService aliasSuggestionService,
                                   BlacklistService blacklistService) {
        this.aliasService = aliasService;
        this.aliasSuggestionService = aliasSuggestionService;
        this.blacklistService = blacklistService;
    }

    /**
     * Approves an alias suggestion.
     * Consequently, the alias suggestion becomes an alias.
     * The caller has to be an admin.
     *
     * @param aliasSuggestion   The alias suggestion to approve.
     * @param mapID             The mapID for which the alias suggestion serves as an additional description.
     * @return                  True if the approval of the alias suggestion succeeds, otherwise false.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean approveAliasSuggestion(String aliasSuggestion, int mapID) {
        if (!aliasSuggestionService.removeAliasSuggestion(aliasSuggestion, mapID)) {
            return false;
        }
        return aliasService.addAlias(aliasSuggestion, mapID);
    }

    /**
     * Disapproves an alias suggestion.
     * Consequently, the alias suggestion is removed.
     * The caller has to be an admin.
     *
     * @param aliasSuggestion   The alias suggestion to disapprove.
     * @param mapID             The mapID for which the alias suggestion serves as an additional description.
     * @return                  True if the disapproval of the alias suggestion succeeds, otherwise false.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean disapproveAliasSuggestion(String aliasSuggestion, int mapID) {
        return aliasSuggestionService.removeAliasSuggestion(aliasSuggestion, mapID);
    }

    /**
     * Votes for an alias suggestion.
     * The caller has to be a user.
     *
     * @param aliasSuggestion   The alias suggestion to vote for.
     * @param mapID             The mapID for which the alias suggestion serves as an additional description.
     * @param user              The user to vote for the alias suggestion.
     * @param vote              The vote of the user for the alias suggestion.
     * @return                  True if the evaluation of the alias suggestion succeeds, otherwise false.
     */
    @PreAuthorize("hasAuthority('USER')")
    public Boolean voteForAliasSuggestion(String aliasSuggestion, int mapID, String user, Boolean vote) {
        return aliasSuggestionService.voteForAlias(aliasSuggestion, mapID, user, vote);
    }

    /**
     * Suggests an alias.
     * Consequently, an alias suggestion is added.
     * The caller has to be a user.
     *
     * @param aliasSuggestion   The alias suggestion to add.
     * @param mapID             The mapID for which the alias suggestion serves as an additional description.
     * @param user              The suggester of the alias.
     * @return                  True if the suggestion of the alias succeeds, otherwise false.
     */
    @PreAuthorize("hasAuthority('USER')")
    public Boolean suggestAlias(String aliasSuggestion, int mapID, String user) {
        if (blacklistService.isBlacklisted(aliasSuggestion)) {
            return false;
        }
        return aliasSuggestionService.addAliasSuggestion(aliasSuggestion, mapID, user);
    }

}
