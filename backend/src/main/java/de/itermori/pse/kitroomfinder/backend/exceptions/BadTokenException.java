package de.itermori.pse.kitroomfinder.backend.exceptions;

public class BadTokenException extends RuntimeException {

    @Override
    public String getMessage() {
        return "invalid Token";
    }
}