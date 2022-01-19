package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.MapID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MapIDRepository extends JpaRepository<MapID, Long> {

    @Query("SELECT m FROM MapID AS m WHERE m.mapID=:alias")
    public String findByID(@Param("mapID") int mapID);

}