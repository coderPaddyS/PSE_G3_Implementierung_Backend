package de.itermori.pse.kitroomfinder.backend.Exceptions;

public class UserAlreadyRegisteredException extends RuntimeException {

    @Override
    public String getMessage() {
        return "User already registered";
    }
}