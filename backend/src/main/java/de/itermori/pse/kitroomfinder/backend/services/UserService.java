package de.itermori.pse.kitroomfinder.backend.services;

public interface UserService {

    public Boolean addUser(String accessToken);
    public User loadUserVyToken(String accessToken);

}
