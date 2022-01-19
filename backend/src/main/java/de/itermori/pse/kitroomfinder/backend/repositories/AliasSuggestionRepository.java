package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.AliasSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AliasSuggestionRepository extends JpaRepository<AliasSuggestion, Long> {
}