package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.exceptions.NoSuchAliasSuggestionException;
import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasSuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides a service for the model {@link AliasSuggestion}.
 * Implements the service interface {@link AliasSuggestionService} defines
 * the corresponding GraphQL schema methods related to the model {@link AliasSuggestion}.
 * Uses the classes {@link AliasSuggestionRepository}, {@link BlacklistService}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Service
public class AliasSuggestionServiceImp implements AliasSuggestionService {

    private final AliasSuggestionRepository aliasSuggestionRepository;
    private final BlacklistService blacklistService;

    /**
     * The constructor which initializes the alias suggestion service implementation
     * with the required classes.
     *
     * @param aliasSuggestionRepository   The required {@link AliasRepository}.
     * @param blacklistService            The required {@link BlacklistService}.
     */
    @Autowired
    public AliasSuggestionServiceImp(AliasSuggestionRepository aliasSuggestionRepository,
                                     BlacklistService blacklistService) {
        this.aliasSuggestionRepository = aliasSuggestionRepository;
        this.blacklistService = blacklistService;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public boolean removeAliasSuggestion(String aliasSuggestion, int mapID) {
        aliasSuggestionRepository.deleteByNameAndID(aliasSuggestion, mapID);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public boolean removeAliasSuggestion(String aliasSuggestion) {
        aliasSuggestionRepository.deleteByName(aliasSuggestion);
        return true;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<AliasSuggestion> getAliasSuggestions(int minValToShowPos, int minValToShowNeg) {
        return aliasSuggestionRepository.findByVotes(minValToShowPos, minValToShowNeg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<AliasSuggestion> getAliasSuggestionsAmount(int mapID, int amount, String user) {
        return aliasSuggestionRepository.findAmount( mapID, amount, "%" + user + "%");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAmountEntriesAliasSuggestion() {
        return String.valueOf(aliasSuggestionRepository.count());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getPosVotes(String aliasSuggestion, int mapID) {
        if (aliasSuggestionRepository.findByNameAndMapID(aliasSuggestion, mapID) == null) {
            throw new NoSuchAliasSuggestionException();
        }
        return aliasSuggestionRepository.getPosVotes(aliasSuggestion, mapID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getNegVotes(String aliasSuggestion, int mapID) {
        if (aliasSuggestionRepository.findByNameAndMapID(aliasSuggestion, mapID) == null) {
            throw new NoSuchAliasSuggestionException();
        }
        return aliasSuggestionRepository.getNegVotes(aliasSuggestion, mapID);
    }

}
