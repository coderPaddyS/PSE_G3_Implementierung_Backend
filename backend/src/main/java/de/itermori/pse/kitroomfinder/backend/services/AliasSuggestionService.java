package de.itermori.pse.kitroomfinder.backend.services;

public interface AliasSuggestionService {

    public Boolean addAliasSuggestoin(String aliasSuggestion, int mapID, String user);
    public Boolean removeAliasSuggestion(String aliasSuggestion, int mapID);
    public Boolean voteForAlias(String aliasSuggestion, int mapID, String user, boolean vote);
    public Iterable<AliasSuggestion> getAliasSuggestion(int minValToShowPos, int minValToShowNeg);

}
