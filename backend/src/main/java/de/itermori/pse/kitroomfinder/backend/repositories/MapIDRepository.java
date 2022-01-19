package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.MapID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapIDRepository extends JpaRepository<MapID, Long> {
}