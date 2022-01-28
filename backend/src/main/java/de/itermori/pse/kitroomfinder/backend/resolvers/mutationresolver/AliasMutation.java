package de.itermori.pse.kitroomfinder.backend.resolvers.mutationresolver;

import de.itermori.pse.kitroomfinder.backend.services.AliasService;
import de.itermori.pse.kitroomfinder.backend.services.AliasSuggestionService;
import de.itermori.pse.kitroomfinder.backend.services.BlacklistService;
import de.itermori.pse.kitroomfinder.backend.services.DeletedAliasService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class AliasMutation implements GraphQLMutationResolver {

    private AliasService aliasService;
    private AliasSuggestionService aliasSuggestionService;
    private DeletedAliasService deletedAliasService;
    private BlacklistService blacklistService;

    @Autowired
    public AliasMutation(AliasService aliasService, AliasSuggestionService aliasSuggestionService,
                         DeletedAliasService deletedAliasService, BlacklistService blacklistService) {
        this.aliasService = aliasService;
        this.deletedAliasService = deletedAliasService;
        this.blacklistService = blacklistService;
        this.aliasSuggestionService = aliasSuggestionService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean removeAlias(String alias) {
        return aliasService.removeAlias(alias);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean blacklistAlias(String toBlacklist) {
        if (!blacklistService.isBlacklisted(toBlacklist)) {
            aliasService.removeAlias(toBlacklist);
            aliasSuggestionService.removeAliasSuggestion(toBlacklist);
            return blacklistService.addToBlacklist(toBlacklist);
        }
        return false;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean removeFromBlacklist(String blacklistedToRem) {
        if (blacklistService.isBlacklisted(blacklistedToRem)) {
            return blacklistService.removeFromBlacklist(blacklistedToRem);
        }
        return false;
    }
}
