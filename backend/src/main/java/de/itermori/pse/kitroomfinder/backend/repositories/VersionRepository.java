package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface VersionRepository extends JpaRepository<Version, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Version a SET a.currentVersion = a.currentVersion + 1")
    public void incrementVersion();

    @Modifying
    @Transactional
    @Query("UPDATE Version a SET a.currentVersion = 1")
    public void initiateVersion();

    @Query("SELECT v.currentVersion FROM Version AS v")
    public Integer retrieveCurrentVersion();

}
