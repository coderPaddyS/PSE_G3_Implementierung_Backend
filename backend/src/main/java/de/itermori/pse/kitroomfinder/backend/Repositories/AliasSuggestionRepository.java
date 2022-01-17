package de.itermori.pse.kitroomfinder.backend.Repositories;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import org.springframework.stereotype.Repository;

@Repository
public class AliasSuggestionRepository {

    public boolean deleteByNameAndID(int mapID, String alias) {
        return true;
    }

    public Iterable<AliasSuggestion> findByVotes(int minVotesNeg, int minVotesPos) {
        return null;
    }

    public boolean updateVotes(int mapID, String alias, boolean vote) {
        return true;
    }

    public boolean addVoter(String user, String aliasSuggestion, int mapID) {
        return true;
    }

    //deleteAfter
    public boolean save(AliasSuggestion alias) {
        return true;
    }

    public Iterable<AliasSuggestion> findAll() {
        return null;
    }
}
