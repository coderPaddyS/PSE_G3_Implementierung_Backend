package de.itermori.pse.kitroomfinder.backend.resolvers.mutationresolver;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.services.AliasService;
import de.itermori.pse.kitroomfinder.backend.services.AliasSuggestionService;
import de.itermori.pse.kitroomfinder.backend.services.BlacklistService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

/**
 * Provides a {@link GraphQLMutationResolver} for the model {@link Alias}.
 * Uses the services {@link AliasService}, {@link AliasSuggestionService},
 * {@link BlacklistService}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Component
public class AliasMutation implements GraphQLMutationResolver {

    private final AliasService aliasService;
    private final AliasSuggestionService aliasSuggestionService;
    private final BlacklistService blacklistService;

    /**
     * Constructor: Demands for the initialization an {@link AliasService}, an {@link AliasSuggestionService},
     * a {@link BlacklistService}.
     * @param aliasService              The required {@link AliasService}.
     * @param aliasSuggestionService    The required {@link AliasSuggestionService}.
     * @param blacklistService          The required {@link BlacklistService}.
     */
    @Autowired
    public AliasMutation(AliasService aliasService, AliasSuggestionService aliasSuggestionService,
                         BlacklistService blacklistService) {
        this.aliasService = aliasService;
        this.blacklistService = blacklistService;
        this.aliasSuggestionService = aliasSuggestionService;
    }

    /**
     * Removes an alias with the name provided.
     * The caller has to be an admin.
     *
     * @param alias The name of the alias to remove.
     * @return      True if the removal of the alias succeeds, otherwise false.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean removeAlias(String alias, int mapID) {
        return aliasService.removeAlias(alias, mapID);
    }

    /**
     * Blacklists an alias with the name provided.
     * The caller has to be an admin.
     *
     * @param toBlacklist   The {@link String} to blacklist.
     * @return              True if the addition of the {@link String} to the blacklist succeeds, otherwise false
     *                      (e.g. when the {@link String} is already blacklisted).
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean blacklistAlias(String toBlacklist) {
        if (!blacklistService.isBlacklisted(toBlacklist)) {
            Iterable<Alias> aliasesToRemove = aliasService.getAliasByName(toBlacklist);
            for (Alias toRemove : aliasesToRemove) {
                aliasService.removeAlias(toRemove.getName(), toRemove.getMapID());
            }
            aliasSuggestionService.removeAliasSuggestion(toBlacklist);
            return blacklistService.addToBlacklist(toBlacklist);
        }
        return false;
    }

    /**
     * Removes an alias from the blacklist.
     * The caller has to be an admin.
     *
     * @param blacklistedToRem  The blacklisted {@link String} to remove from the blacklist.
     * @return                  True if the removal of the {@link String} from the blacklist succeeds, otherwise false
     *                          (e.g. when the {@link String} is not blacklisted).
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean removeFromBlacklist(String blacklistedToRem) {
        if (blacklistService.isBlacklisted(blacklistedToRem)) {
            return blacklistService.removeFromBlacklist(blacklistedToRem);
        }
        return false;
    }

}
