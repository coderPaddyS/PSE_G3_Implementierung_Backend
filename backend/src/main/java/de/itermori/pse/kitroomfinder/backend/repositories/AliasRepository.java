package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AliasRepository extends JpaRepository<Alias, Long> {

    @Query("SELECT a FROM Alias AS a WHERE a.mapID=:mapID")
    public Iterable<Alias> findByMapID(@Param("mapID") int mapID);

    @Query("SELECT a FROM Alias AS a WHERE a.name=:alias")
    public Iterable<Alias> findByName(@Param("alias") String alias);

    @Transactional
    public void deleteByName(String name);


    //public void alreadyExists(@Parameter("name") String name);

    @Query("SELECT a FROM Alias AS a WHERE a.version>:version")
    public Iterable<Alias> findUpdatesByVersion(@Param("version")int version);
    
}
