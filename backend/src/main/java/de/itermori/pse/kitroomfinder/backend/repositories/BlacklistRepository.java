package de.itermori.pse.kitroomfinder.backend.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class BlacklistRepository {

    public boolean deleteByName(String alias) {
        return true;
    }

    //deleteAfter
    public boolean save(String alias) {
        return true;
    }

    public Iterable<String> findAll() {
        return null;
    }
}