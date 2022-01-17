package de.itermori.pse.kitroomfinder.backend.resolvers.mutationresolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AliasSuggestionMutation implements GraphQLMutationResolver {

    private AliasService aliasService;
    private AliasSuggestionService aliasSuggestionService;
    private BlacklistService blacklistService;

    @Autowired
    public AliasSuggestionMutation(AliasService aliasService, UserService userService, AliasSuggestionService aliasSuggestionService,
                                   BlacklistService blacklistService, DeletedAliasService deletedAliasService) {
        this.aliasService = aliasService;
        this.userService = userService;
        this.aliasSuggestionService = aliasSuggestionService;
        this.blacklistService = blacklistService;
        this.deletedAliasService = deletedAliasService;
    }

    public Boolean approveAliasSuggestion(String aliasSuggestion, int mapID) {
        if (!aliasSuggestionService.removeAliasSuggestion(aliasSuggestion, mapID)) {
            return false;
        }
        return aliasService.addAlias(aliasSuggestion, mapID);
    }

    public Boolean disapproveAliasSuggestion(String aliasSuggestion, int mapID) {
        return aliasSuggestionService.removeAliasSuggestion(aliasSuggestion, mapID);
    }

    public Boolean voteForAliasSuggestion(String aliasSuggestion, int mapID, String user, Boolean vote) {
        return aliasSuggestionService.voteForAlias(aliasSuggestion, mapID, user, vote);
    }

    public Boolean removeAliasSuggestion(String aliasSuggestion, int mapID) {
        return aliasSuggestionService.removeAliasSuggestion(aliasSuggestion, mapID);
    }

    public Boolean suggestAlias(String aliasSuggestion, int mapID, String user) {
        if (blacklistService.isBlacklisted(aliasSuggestion)) {
            return false;
        }
        return aliasSuggestionService.addAliasSuggestion(aliasSuggestion, mapID, user);
    }
}
