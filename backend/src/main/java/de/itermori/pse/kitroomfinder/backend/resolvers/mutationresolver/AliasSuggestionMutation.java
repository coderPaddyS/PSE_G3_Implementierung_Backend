package de.itermori.pse.kitroomfinder.backend.resolvers.mutationresolver;

import de.itermori.pse.kitroomfinder.backend.services.AliasService;
import de.itermori.pse.kitroomfinder.backend.services.AliasSuggestionService;
import de.itermori.pse.kitroomfinder.backend.services.BlacklistService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class AliasSuggestionMutation implements GraphQLMutationResolver {

    private AliasService aliasService;
    private AliasSuggestionService aliasSuggestionService;
    private BlacklistService blacklistService;

    @Autowired
    public AliasSuggestionMutation(AliasService aliasService, AliasSuggestionService aliasSuggestionService,
                                   BlacklistService blacklistService) {
        this.aliasService = aliasService;
        this.aliasSuggestionService = aliasSuggestionService;
        this.blacklistService = blacklistService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean approveAliasSuggestion(String aliasSuggestion, int mapID, String mapObject) {
        if (!aliasSuggestionService.removeAliasSuggestion(aliasSuggestion, mapID)) {
            return false;
        }
        return aliasService.addAlias(aliasSuggestion, mapID, mapObject);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean disapproveAliasSuggestion(String aliasSuggestion, int mapID) {
        return aliasSuggestionService.removeAliasSuggestion(aliasSuggestion, mapID);
    }

    @PreAuthorize("hasAuthority('USER')")
    public Boolean voteForAliasSuggestion(String aliasSuggestion, int mapID, String user, Boolean vote) {
        return aliasSuggestionService.voteForAlias(aliasSuggestion, mapID, user, vote);
    }

    @PreAuthorize("hasAuthority('USER')")
    public Boolean suggestAlias(String aliasSuggestion, int mapID, String mapObject, String user) {
        if (blacklistService.isBlacklisted(aliasSuggestion)) {
            return false;
        }
        return aliasSuggestionService.addAliasSuggestion(aliasSuggestion, mapID, mapObject, user);
    }
}
