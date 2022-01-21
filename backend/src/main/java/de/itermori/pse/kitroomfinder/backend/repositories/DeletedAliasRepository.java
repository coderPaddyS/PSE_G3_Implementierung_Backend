package de.itermori.pse.kitroomfinder.backend.repositories;


import de.itermori.pse.kitroomfinder.backend.models.Alias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeletedAliasRepository extends JpaRepository<Alias, Long> {

    @Query("SELECT a FROM Alias AS a WHERE a.version>=:version")
    public Iterable<Alias> findNewerThanVersion(@Param("version")int version);

    public void deleteByNameAndMapID(String name, int mapID);
}
