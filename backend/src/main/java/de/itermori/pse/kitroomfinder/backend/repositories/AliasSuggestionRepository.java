package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides a {@link JpaRepository} interface for the model {@link AliasSuggestion}.
 * Communicates with the database, more precisely with the table regarding the alias suggestions.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
public interface AliasSuggestionRepository extends JpaRepository<AliasSuggestion, Long> {

    /**
     * Deletes the alias suggestion (type: {@link AliasSuggestion}) from the database
     * which serves as a description of the provided mapID and whose
     * name corresponds to the provided aliasSuggestion.
     * If no such alias suggestion {type: {@link AliasSuggestion}} is stored in the database,
     * no action is taken.
     *
     * @param mapID The mapID which the to-be-deleted alias suggestion has to serve as a description for.
     * @param aliasSuggestion The name which the to-be-deleted alias suggestion has to have.
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM AliasSuggestion a WHERE a.name=:aliasSuggestion AND a.mapID=:mapID")
    void deleteByNameAndID(@Param("aliasSuggestion")String aliasSuggestion, @Param("mapID")int mapID);

    /**
     * Deletes all alias suggestions (type: {@link AliasSuggestion}) from the database
     * whose names correspond to the provided aliasSuggestion.
     *
     * @param aliasSuggestion The name which the to-be-deleted alias suggestions have to have.
     */
    @Transactional
    void deleteByName(String aliasSuggestion);

    /**
     * Finds all alias suggestions (type: {@link AliasSuggestion}) from the database
     * which have at least the provided minVotesPos and minVotesNeg.
     *
     * @param minVotesPos   The min of negative votes that an alias suggestion has to have to be returned.
     * @param minVotesNeg   The min of positive votes that an alias suggestion has to have to be returned.
     * @return              An {@link Iterable} of all alias suggestions (type: {@link AliasSuggestion}
     *                      which have at least the provided minVotesPos and minVotesNeg.
     */
    @Query("SELECT a FROM AliasSuggestion AS a WHERE a.posVotes>=:minVotesPos AND a.negVotes>=:minVotesNeg")
    Iterable<AliasSuggestion> findByVotes(@Param("minVotesPos") int minVotesPos,
                                                 @Param("minVotesNeg") int minVotesNeg);

    /**
     * Finds at maximum amount alias suggestions (type: {@link AliasSuggestion} from the database
     * which serve as a description of the provided mapID and which the provided user evaluated.
     *
     * @param mapID     The mapID which the alias suggestions have to serve as a description for.
     * @param amount    The maximum amount of alias suggestions that should be returned.
     * @param user      The user who has to have evaluated the alias suggestions.
     * @return          An {@link Iterable} of maximum amount alias suggestions which serve as a description
     *                  of the provided mapID and were evaluated by the provided user.
     */
    @Query(value = "SELECT * FROM alias_suggestion WHERE mapID=:mapID AND voters LIKE :user LIMIT :amount",
            nativeQuery = true)
    Iterable<AliasSuggestion> findAmount(@Param("mapID") int mapID, @Param("amount") int amount,
                                         @Param("user") String user);

    /**
     * Adds the provided voter as a voter to the provided aliasSuggestion.
     * The aliasSuggestion has to serve as a description for the provided mapID.
     *
     * @param aliasSuggestion   The alias suggestion to which the voter should be added as a voter.
     * @param mapID             The mapID which the aliasSuggestion has to serve as a description for.
     * @param voter             The voter which is to be added as a voter to the provided
     *                          aliasSuggestion.
     */
    @Modifying
    @Transactional
    @Query("UPDATE AliasSuggestion a SET a.voters = CONCAT(CONCAT(a.voters,','),:voter) WHERE " +
            "a.mapID=:mapID AND a.name=:aliasSuggestion")
    void addVoter(@Param("aliasSuggestion") String aliasSuggestion, @Param("mapID") int mapID,
                         @Param("voter") String voter);

    /**
     * Upvotes the alias suggestion (type: {@link AliasSuggestion}) stored in the database
     * which serves as a description for the provided mapID and whose name corresponds
     * to the provided aliasSuggestion.
     *
     * @param mapID             The mapID which the alias suggestion has to serve as a description for.
     * @param aliasSuggestion   The name of the alias suggestion.
     */
    @Modifying
    @Transactional
    @Query("UPDATE AliasSuggestion a SET a.posVotes = a.posVotes + 1 WHERE a.mapID=:mapID AND a.name=:aliasSuggestion")
    void votePos(@Param("mapID") int mapID, @Param("aliasSuggestion")String aliasSuggestion);

    /**
     * Downvotes the alias suggestion (type: {@link AliasSuggestion}) stored in the database
     * which serves as a description for the provided mapID and whose name corresponds
     * to the provided aliasSuggestion.
     *
     * @param mapID             The mapID which the alias suggestion has to serve as a description for.
     * @param aliasSuggestion   The name of the alias suggestion.
     */
    @Modifying
    @Transactional
    @Query("UPDATE AliasSuggestion a SET a.negVotes = a.negVotes + 1 WHERE a.mapID=:mapID AND a.name=:aliasSuggestion")
    void voteNeg(@Param("mapID") int mapID, @Param("aliasSuggestion")String aliasSuggestion);

    /**
     * Finds the alias suggestion (type: {@link AliasSuggestion} stored in the database
     * whose name corresponds to the provided aliasSuggestion and which serves
     * as a description for the provided mapID.
     *
     * @param aliasSuggestion   The name which the alias suggestion has to have.
     * @param mapID             The mapID which the alias suggestion has to serve as a description for.
     * @return                  The {@link AliasSuggestion} whose name corresponds to the provided
     *                          aliasSuggestion and which serves
     *                          as a description for the provided mapID.
     */
    @Query("SELECT a FROM AliasSuggestion AS a WHERE a.name=:aliasSuggestion AND a.mapID=:mapID")
    AliasSuggestion findByNameAndMapID(@Param("aliasSuggestion") String aliasSuggestion,
                                    @Param("mapID") int mapID);

    /**
     * Returns the amount of positive votes that the alias suggestion (type: {@link AliasSuggestion}
     * stored in the database has. The name of the alias suggestion has to correspond
     * to aliasSuggestion and it has to serve as a description for the provided mapID.
     *
     * @param aliasSuggestion   The name which the alias suggestion has to have.
     * @param mapID             The mapID which the alias suggestion has to serve as a description for.
     * @return                  The amount of positive votes that the desired alias suggestion has.
     */
    @Query("SELECT a.posVotes FROM AliasSuggestion AS a WHERE a.name=:aliasSuggestion AND a.mapID=:mapID")
    Integer getPosVotes(@Param("aliasSuggestion") String aliasSuggestion, @Param("mapID") int mapID);

    /**
     * Returns the amount of negative votes that the alias suggestion (type: {@link AliasSuggestion}
     * stored in the database has. The name of the alias suggestion has to correspond
     * to aliasSuggestion and it has to serve as a description for the provided mapID.
     *
     * @param aliasSuggestion   The name which the alias suggestion has to have.
     * @param mapID             The mapID which the alias suggestion has to serve as a description for.
     * @return                  The amount of negative votes that the desired alias suggestion has.
     */
    @Query("SELECT a.negVotes FROM AliasSuggestion AS a WHERE a.name=:aliasSuggestion AND a.mapID=:mapID")
    Integer getNegVotes(@Param("aliasSuggestion") String aliasSuggestion, @Param("mapID") int mapID);

}
