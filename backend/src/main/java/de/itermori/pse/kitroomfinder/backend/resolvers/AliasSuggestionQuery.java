package de.itermori.pse.kitroomfinder.backend.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AliasSuggestionQuery implements GraphQLQueryResolver{

    private AliasSuggestionService aliasSuggestionService;

    @Autowired
    public AliasSuggestionQuery(AliasSuggestionService  aliasSuggestionService) {
        this.aliasSuggestionService = aliasSuggestionService;
    }

    public Iterable<AliasSuggestion> getAliasSuggestions(int minValToShowPos,int minValToShowNeg) {
        return aliasSuggestionService.getAliasSuggestions(int minValToShowPos,int minValToShowNeg);
    }

}
