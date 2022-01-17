package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasSuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AliasSuggestionServiceImp implements AliasSuggestionService {

    private AliasSuggestionRepository aliasSuggestionRepository;

    @Autowired
    public AliasSuggestionServiceImp(AliasSuggestionRepository aliasSuggestionRepository) {
        this.aliasSuggestionRepository = aliasSuggestionRepository;
    }

    @Override
    public boolean addAliasSuggestion(String aliasSuggestion, int mapID, String user) {
        return aliasSuggestionRepository.save(new AliasSuggestion(aliasSuggestion, mapID, user));
    }

    @Override
    public boolean removeAliasSuggestion(String aliasSuggestion, int mapID) {
        return aliasSuggestionRepository.deleteByNameAndID(aliasSuggestion, mapID);
    }

    @Override
    public boolean voteForAlias(String aliasSuggestion, int mapID, String user, boolean vote) {
        return aliasSuggestionRepository.updateVotes(aliasSuggestion, mapID, vote);
    }
}
