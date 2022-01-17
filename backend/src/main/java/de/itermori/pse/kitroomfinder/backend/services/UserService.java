package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.User;

public interface UserService {

    public String addUser(String accessToken);
    public User loadUserByToken(String accessToken);

}
