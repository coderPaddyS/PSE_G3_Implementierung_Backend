package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.MapObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MapObjectRepository extends JpaRepository<MapObject, Long> {

    @Query("SELECT m.name FROM MapObject AS m WHERE m.mapID=:mapID")
    public String findByID(@Param("mapID") int mapID);

    @Query("SELECT m.mapID FROM MapObject AS m WHERE m.name=:mapObject")
    public int findByName(@Param("mapObject") String mapObject);

}
