package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import de.itermori.pse.kitroomfinder.backend.models.User;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasSuggestionRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AliasSuggestionServiceImp implements AliasSuggestionService {

    private AliasSuggestionRepository aliasSuggestionRepository;

    @Autowired
    public AliasSuggestionServiceImp(AliasSuggestionRepository aliasSuggestionRepository) {
        this.aliasSuggestionRepository = aliasSuggestionRepository;
    }

    @Transactional
    @Override
    public boolean addAliasSuggestion(String aliasSuggestion, int mapID, String user) {
        if (aliasSuggestionRepository.findByNameAndMapID(aliasSuggestion, mapID) != null){
            return false;
        }
        aliasSuggestionRepository.save(new AliasSuggestion(aliasSuggestion, mapID, user));
        return true;
    }

    @Transactional
    @Override
    public boolean removeAliasSuggestion(String aliasSuggestion, int mapID) {
        if (aliasSuggestionRepository.findByNameAndMapID(aliasSuggestion, mapID) == null){
            return false;
        }
        aliasSuggestionRepository.deleteByNameAndID(mapID, aliasSuggestion);
        return true;
    }

    @Transactional
    @Override
    public boolean removeAliasSuggestion(String aliasSuggestion) {
        aliasSuggestionRepository.deleteByName(aliasSuggestion);
        return true;
    }

    @Transactional
    @Override
    public boolean voteForAlias(String aliasSuggestion, int mapID, String user, boolean vote) {
        if (aliasSuggestionRepository.findByNameAndMapID(aliasSuggestion, mapID) == null){
            return false;
        }
        if (vote) {
            aliasSuggestionRepository.votePos(mapID, aliasSuggestion);
        } else {
            aliasSuggestionRepository.voteNeg(mapID, aliasSuggestion);
        }
        AliasSuggestion aliasSuggestionEntity = aliasSuggestionRepository.findByNameAndMapID(aliasSuggestion, mapID);
        aliasSuggestionEntity.getVoters().add(user);
        return true;
    }

    @Override
    public Iterable<AliasSuggestion> getAliasSuggestions(int minValToShowPos, int minValToShowNeg) {
        return aliasSuggestionRepository.findByVotes(minValToShowPos, minValToShowNeg);
    }
    
}
