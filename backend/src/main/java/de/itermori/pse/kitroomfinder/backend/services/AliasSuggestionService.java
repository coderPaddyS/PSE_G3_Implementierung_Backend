package de.itermori.pse.kitroomfinder.backend.services;

public interface AliasSuggestionService {

    public boolean addAliasSuggestoin(String aliasSuggestion, int mapID, String user);
    public boolean removeAliasSuggestion(String aliasSuggestion, int mapID);
    public boolean voteForAlias(String aliasSuggestion, int mapID, String user, boolean vote);
    public Iterable<AliasSuggestion> getAliasSuggestion(int minValToShowPos, int minValToShowNeg);

}
