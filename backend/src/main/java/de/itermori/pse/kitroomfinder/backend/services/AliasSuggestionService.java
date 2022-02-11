package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;

public interface AliasSuggestionService {

    public boolean addAliasSuggestion(String aliasSuggestion, int mapID, String mapObject, String user);
    public boolean removeAliasSuggestion(String aliasSuggestion, int mapID);
    public boolean removeAliasSuggestion(String aliasSuggestion);
    public boolean voteForAlias(String aliasSuggestion, int mapID, String user, boolean vote);
    public Iterable<AliasSuggestion> getAliasSuggestions(int minValToShowPos, int minValToShowNeg);
    public Iterable<AliasSuggestion> getAliasSuggestionsAmount(int mapID, int amount, String user);
    public String getAmountEntriesAliasSuggestion();
    public int getPosVotes(String aliasSuggestion, int mapID);
    public int getNegVotes(String aliasSuggestion, int mapID);

}
