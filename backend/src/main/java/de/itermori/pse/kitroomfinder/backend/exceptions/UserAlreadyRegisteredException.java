package de.itermori.pse.kitroomfinder.backend.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException {

    @Override
    public String getMessage() {
        return "User already registered";
    }
}