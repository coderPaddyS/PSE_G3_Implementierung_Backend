package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.exceptions.NoSuchAliasSuggestionException;
import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasSuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides a service for the model {@link AliasSuggestion}.
 * Implements the service interface {@link AliasSuggestionService} defines
 * the corresponding GraphQL schema methods related to the model {@link AliasSuggestion}.
 * Uses the classes {@link AliasSuggestionRepository}, {@link BlacklistService}, {@link MapObjectService}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Service
public class AliasSuggestionServiceImp implements AliasSuggestionService {

    private final AliasSuggestionRepository aliasSuggestionRepository;
    private final BlacklistService blacklistService;
    private final MapObjectService mapObjectService;

    /**
     * The constructor which initializes the alias suggestion service implementation
     * with the required classes.
     *
     * @param aliasSuggestionRepository   The required {@link AliasSuggestionRepository}.
     * @param blacklistService            The required {@link BlacklistService}.
     * @param mapObjectService            The required {@link MapObjectService}.
     */
    @Autowired
    public AliasSuggestionServiceImp(AliasSuggestionRepository aliasSuggestionRepository,
                                     BlacklistService blacklistService, MapObjectService mapObjectService) {
        this.aliasSuggestionRepository = aliasSuggestionRepository;
        this.blacklistService = blacklistService;
        this.mapObjectService = mapObjectService;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public boolean addAliasSuggestion(String aliasSuggestion, int mapID, String user) {
        if (blacklistService.isBlacklisted(aliasSuggestion)) {
            return false;
        }
        if (aliasSuggestionRepository.findByNameAndMapID(aliasSuggestion, mapID) != null){
            return false;
        }
        String mapObjectName = mapObjectService.getMapObjectName(mapID);
        aliasSuggestionRepository.save(new AliasSuggestion(aliasSuggestion, mapID, mapObjectName, user));
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public boolean removeAliasSuggestion(String aliasSuggestion, int mapID) {
        if (aliasSuggestionRepository.findByNameAndMapID(aliasSuggestion,mapID) == null) {
            return false;
        }
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
    @Transactional(isolation = Isolation.REPEATABLE_READ)
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
