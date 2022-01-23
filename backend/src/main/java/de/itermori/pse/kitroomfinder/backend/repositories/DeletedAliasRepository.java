package de.itermori.pse.kitroomfinder.backend.repositories;


import de.itermori.pse.kitroomfinder.backend.models.Alias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DeletedAliasRepository extends JpaRepository<Alias, Long> {

    @Query("SELECT a FROM Alias AS a WHERE a.version>=:version")
    public Iterable<Alias> findNewerThanVersion(@Param("version")int version);

    @Transactional
    public void deleteByNameAndMapID(String name, int mapID);
}
