package de.itermori.pse.kitroomfinder.backend.security;

public class BadTokenException extends RuntimeException {

    @Override
    public String getMessage() {
        return "invalid Token";
    }
}