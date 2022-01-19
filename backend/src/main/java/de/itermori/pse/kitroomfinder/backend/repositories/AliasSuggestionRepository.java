package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AliasSuggestionRepository extends JpaRepository<AliasSuggestion, Long> {

    @Modifying
    @Query("DELETE FROM Alias a WHERE a.name=:name AND a.mapID=:mapID")
    public void deleteByNameAndID(@Param("mapID")int mapID, @Param("alias")String alias);

    @Query("SELECT a FROM AliasSuggestion AS a WHERE a.posVotes>=:minVotesPos AND a.negVotes>=:minVotesNeg")
    public void findByVotes(@Param("minVotesNeg") String minVotesNeg, @Param("minVotesPos") String minVotesPos);

    @Modifying
    @Query("UPDATE AliasSuggestion a SET a.posVotes = a.posVotes + 1 WHERE a.mapID=:mapID AND a.name=:alias")
    public void votePos(@Param("mapID") int mapID, @Param("alias")String alias);

    @Modifying
    @Query("UPDATE AliasSuggestion a SET a.negVotes = a.negVotes + 1 WHERE a.mapID=:mapID AND a.name=:alias")
    public void voteNeg(@Param("mapID") int mapID, @Param("alias")String alias);

    @Modifying
    @Query("UPDATE AliasSuggestion a SET a.negVotes = a.negVote + 1 WHERE a.mapID=:mapID AND a.name=:alias")
    public void negVotes(@Param("user") String user, @Param("aliasSuggestion")String aliasSuggestion,
                         @Param("mapID") int mapID);
}