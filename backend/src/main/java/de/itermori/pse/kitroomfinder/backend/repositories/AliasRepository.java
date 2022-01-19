package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AliasRepository extends JpaRepository<Alias, Long> {
    Optional<Alias> findByName(String name);
}