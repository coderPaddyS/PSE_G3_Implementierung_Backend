package de.itermori.pse.kitroomfinder.backend.resolvers.mutationresolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Boolean removeAlias(String alias) {
        return aliasService.removeAllias(alias);
    }

    public Boolean blacklistAlias(String toBlacklist) {
        aliasService.removeAlias(toBlacklist);
        aliasSuggestionService.rermoveAliasSuggestion(toBlacklist);
        return blacklistService.addToBlacklist(toBlacklist);
    }

    public Boolean removeFromBlacklist(String blacklistedToRem) {
        return blacklistService.removeFromBlacklist(blacklistedToRem);
    }
}