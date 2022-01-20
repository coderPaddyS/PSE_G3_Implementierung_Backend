package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;

public interface AliasSuggestionService {

    public boolean addAliasSuggestion(String aliasSuggestion, int mapID, String user);
    public boolean removeAliasSuggestion(String aliasSuggestion, int mapID);
    public boolean removeAliasSuggestion(String aliasSuggestion);
    public boolean voteForAlias(String aliasSuggestion, int mapID, String user, boolean vote);
    public Iterable<AliasSuggestion> getAliasSuggestions(int minValToShowPos, int minValToShowNeg);

}
