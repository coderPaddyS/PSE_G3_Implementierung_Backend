package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.BlacklistEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BlacklistRepository extends JpaRepository<BlacklistEntry, Long> {

    @Transactional
    public void deleteByName(String name);
}
