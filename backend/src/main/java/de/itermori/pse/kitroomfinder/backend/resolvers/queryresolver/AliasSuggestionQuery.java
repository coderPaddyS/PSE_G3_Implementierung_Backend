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

    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<AliasSuggestion> getAliasSuggestions(int minValToShowPos, int minValToShowNeg) {
        return aliasSuggestionService.getAliasSuggestions(minValToShowPos,minValToShowNeg);
    }

    @PreAuthorize("hasAuthority('USER')")
    public Iterable<AliasSuggestion> getAliasSuggestionsAmount(int mapID, int amount, String user) {
        return aliasSuggestionService.getAliasSuggestionsAmount(mapID, amount, user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Integer getPosVotes(String aliasSuggestion, int mapID) {
        return aliasSuggestionService.getPosVotes(aliasSuggestion, mapID);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Integer getNegVotes(String aliasSuggestion, int mapID) {
        return aliasSuggestionService.getNegVotes(aliasSuggestion, mapID);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAmountEntriesAliasSuggestion() {
        return aliasSuggestionService.getAmountEntriesAliasSuggestion();
    }

}
