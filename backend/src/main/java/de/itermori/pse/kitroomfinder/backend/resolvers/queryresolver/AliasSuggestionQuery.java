package de.itermori.pse.kitroomfinder.backend.resolvers.queryresolver;


import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import de.itermori.pse.kitroomfinder.backend.services.AliasSuggestionService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class AliasSuggestionQuery implements GraphQLQueryResolver {

    private AliasSuggestionService aliasSuggestionService;

    @Autowired
    public AliasSuggestionQuery(AliasSuggestionService aliasSuggestionService) {
        this.aliasSuggestionService = aliasSuggestionService;
    }

    @PreAuthorize("hasAuthority('USER')")
    public Iterable<AliasSuggestion> getAliasSuggestions(int minValToShowPos, int minValToShowNeg) {
        return aliasSuggestionService.getAliasSuggestions(minValToShowPos,minValToShowNeg);
    }

    public Iterable<AliasSuggestion> getAliasSuggestionsAmount(int mapID, int amount, String user) {
        return aliasSuggestionService.getAliasSuggestionsAmount(mapID, amount, user);
    }

}
