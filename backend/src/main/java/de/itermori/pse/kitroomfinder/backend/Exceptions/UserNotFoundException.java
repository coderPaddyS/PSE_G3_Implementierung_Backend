package de.itermori.pse.kitroomfinder.backend.Exceptions;

public class UserNotFoundException extends RuntimeException{

    @Override
    public String getMessage() {
        return "User not registered yet";
    }

}
