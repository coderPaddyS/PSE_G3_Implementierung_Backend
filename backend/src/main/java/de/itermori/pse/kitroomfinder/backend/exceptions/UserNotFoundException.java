package de.itermori.pse.kitroomfinder.backend.exceptions;

public class UserNotFoundException extends RuntimeException{

    @Override
    public String getMessage() {
        return "User not registered yet";
    }

}
