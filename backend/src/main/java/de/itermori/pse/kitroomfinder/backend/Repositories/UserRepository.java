package de.itermori.pse.kitroomfinder.backend.Repositories;

import de.itermori.pse.kitroomfinder.backend.models.MapID;
import de.itermori.pse.kitroomfinder.backend.models.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public User findByName(String user) {
        return new User("test", null);
    }

    //deleteAfter
    public boolean safe(User alias) {
        return true;
    }

    public Iterable<User> findAll() {
        return null;
    }
}
