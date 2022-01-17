package de.itermori.pse.kitroomfinder.backend.resolvers.mutationresolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.itermori.pse.kitroomfinder.backend.services.AliasService;
import de.itermori.pse.kitroomfinder.backend.services.AliasSuggestionService;
import de.itermori.pse.kitroomfinder.backend.services.BlacklistService;
import de.itermori.pse.kitroomfinder.backend.services.DeletedAliasService;
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
        return aliasService.removeAlias(alias);
    }

    public Boolean blacklistAlias(String toBlacklist, int mapID) {
        aliasService.removeAlias(toBlacklist);
        aliasSuggestionService.removeAliasSuggestion(toBlacklist, mapID);
        return blacklistService.addToBlacklist(toBlacklist);
    }

    public Boolean removeFromBlacklist(String blacklistedToRem) {
        return blacklistService.removeFromBlacklist(blacklistedToRem);
    }
}
