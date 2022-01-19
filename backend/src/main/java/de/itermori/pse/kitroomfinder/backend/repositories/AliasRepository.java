package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AliasRepository extends JpaRepository<Alias, Long> {

    @Query("SELECT a FROM Alias AS a WHERE a.mapID=:mapID")
    public Iterable<Alias> findByMapID(@Param("mapID") int mapID);

    @Query("SELECT a FROM Alias AS a WHERE a.name=:alias")
    public Iterable<Alias> findByName(@Param("alias") String alias);

    @Query ("DELETE FROM Alias a WHERE a.name=:name")
    public void deleteByName(@Param("name")String name);
}