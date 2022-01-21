package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AliasSuggestionRepository extends JpaRepository<AliasSuggestion, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM AliasSuggestion a WHERE a.name=:alias AND a.mapID=:mapID")
    public void deleteByNameAndID(@Param("mapID")int mapID, @Param("alias")String alias);

    public void deleteByName(String alias);

    @Query("SELECT a FROM AliasSuggestion AS a WHERE a.posVotes>=:minVotesPos AND a.negVotes>=:minVotesNeg")
    public Iterable<AliasSuggestion> findByVotes(@Param("minVotesNeg") int minVotesNeg,
                                                 @Param("minVotesPos") int minVotesPos);

    @Transactional
    @Query("UPDATE AliasSuggestion a SET a.posVotes = a.posVotes + 1 WHERE a.mapID=:mapID AND a.name=:alias")
    public void votePos(@Param("mapID") int mapID, @Param("alias")String alias);

    @Transactional
    @Query("UPDATE AliasSuggestion a SET a.negVotes = a.negVotes + 1 WHERE a.mapID=:mapID AND a.name=:alias")
    public void voteNeg(@Param("mapID") int mapID, @Param("alias")String alias);

    @Query("SELECT a FROM AliasSuggestion AS a WHERE a.name=:aliasSuggestion AND a.mapID=:mapID")
    public AliasSuggestion findByNameAndMapID(@Param("aliasSuggestion") String aliasSuggestion,
                                    @Param("mapID") int mapID);
}
