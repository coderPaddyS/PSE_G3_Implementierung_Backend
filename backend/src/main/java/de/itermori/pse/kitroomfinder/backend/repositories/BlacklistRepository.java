package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.BlacklistEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BlacklistRepository extends JpaRepository<BlacklistEntry, Long> {

    public void deleteByName(String name);
}
