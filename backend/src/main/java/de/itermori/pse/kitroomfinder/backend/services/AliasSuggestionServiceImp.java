package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasSuggestionRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AliasSuggestionServiceImp implements AliasSuggestionService {

    private AliasSuggestionRepository aliasSuggestionRepository;
    private UserRepository userRepository;

    @Autowired
    public AliasSuggestionServiceImp(AliasSuggestionRepository aliasSuggestionRepository) {
        this.aliasSuggestionRepository = aliasSuggestionRepository;
    }

    @Transactional
    @Override
    public boolean addAliasSuggestion(String aliasSuggestion, int mapID, String user) {
        aliasSuggestionRepository.save(new AliasSuggestion(aliasSuggestion, mapID, user));
        return true;
    }

    @Transactional
    @Override
    public boolean removeAliasSuggestion(String aliasSuggestion, int mapID) {
        aliasSuggestionRepository.deleteByNameAndID(mapID, aliasSuggestion);
        return true;
    }

    @Transactional
    @Override
    public boolean voteForAlias(String aliasSuggestion, int mapID, String user, boolean vote) {
        if (vote) {
            aliasSuggestionRepository.votePos(mapID, aliasSuggestion);
        } else {
            aliasSuggestionRepository.voteNeg(mapID, aliasSuggestion);
        }
        List<String> voters = aliasSuggestionRepository.getAllVoters(user, aliasSuggestion, mapID);
        voters.add(user);
        return true;
    }

    @Override
    public Iterable<AliasSuggestion> getAliasSuggestions(int minValToShowPos, int minValToShowNeg) {
        return aliasSuggestionRepository.findByVotes(minValToShowPos, minValToShowNeg);
    }
    
}
