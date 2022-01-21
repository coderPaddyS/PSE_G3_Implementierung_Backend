package de.itermori.pse.kitroomfinder.backend.Exceptions;

public class BadTokenException extends RuntimeException {

    @Override
    public String getMessage() {
        return "invalid Token";
    }
}