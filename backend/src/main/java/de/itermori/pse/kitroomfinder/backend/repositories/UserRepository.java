package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public User findByName(String user) {
        return new User("test", null);
    }

    //deleteAfter
    public boolean save(User alias) {
        return true;
    }

    public Iterable<User> findAll() {
        return null;
    }
}