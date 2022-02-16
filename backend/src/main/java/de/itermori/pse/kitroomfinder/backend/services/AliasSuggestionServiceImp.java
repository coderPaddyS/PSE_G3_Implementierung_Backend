package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasSuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AliasSuggestionServiceImp implements AliasSuggestionService {

    private final AliasSuggestionRepository aliasSuggestionRepository;
    private final BlacklistService blacklistService;

    @Autowired
    public AliasSuggestionServiceImp(AliasSuggestionRepository aliasSuggestionRepository,
                                     BlacklistService blacklistService) {
        this.aliasSuggestionRepository = aliasSuggestionRepository;
        this.blacklistService = blacklistService;
    }

    @Transactional
    @Override
    public boolean addAliasSuggestion(String aliasSuggestion, int mapID, String mapObject, String user) {
        if (blacklistService.isBlacklisted(aliasSuggestion)) {
            return false;
        }
        if (aliasSuggestionRepository.findByNameAndMapID(aliasSuggestion, mapID) != null){
            return false;
        }
        aliasSuggestionRepository.save(new AliasSuggestion(aliasSuggestion, mapID, mapObject, user));
        return true;
    }

    @Transactional
    @Override
    public boolean removeAliasSuggestion(String aliasSuggestion, int mapID) {
        if (aliasSuggestionRepository.findByNameAndMapID(aliasSuggestion, mapID) == null){
            return false;
        }
        aliasSuggestionRepository.deleteByNameAndID(aliasSuggestion, mapID);
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
        if (aliasSuggestionRepository.findByNameAndMapID(aliasSuggestion, mapID)
            .getVoters().contains(user)) {
            return false;
        }
        if (vote) {
            aliasSuggestionRepository.votePos(mapID, aliasSuggestion);
        } else {
            aliasSuggestionRepository.voteNeg(mapID, aliasSuggestion);
        }
        aliasSuggestionRepository.addVoter(aliasSuggestion, mapID, user);

        return true;
    }

    @Override
    public Iterable<AliasSuggestion> getAliasSuggestions(int minValToShowPos, int minValToShowNeg) {
        return aliasSuggestionRepository.findByVotes(minValToShowPos, minValToShowNeg);
    }

    @Override
    public Iterable<AliasSuggestion> getAliasSuggestionsAmount(int mapID, int amount, String user) {
        return aliasSuggestionRepository.findAmount( mapID, amount, "%" + user + "%");
    }

    @Override
    public String getAmountEntriesAliasSuggestion() {
        return String.valueOf(aliasSuggestionRepository.count());
    }

    @Override
    public int getPosVotes(String aliasSuggestion, int mapID) {
        return aliasSuggestionRepository.getPosVotes(aliasSuggestion, mapID);
    }

    @Override
    public int getNegVotes(String aliasSuggestion, int mapID) {
        return aliasSuggestionRepository.getNegVotes(aliasSuggestion, mapID);
    }

}
