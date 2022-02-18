package de.itermori.pse.kitroomfinder.backend.exceptions;

public class NoSuchAliasSuggestionException extends RuntimeException {

    @Override
    public String getMessage() {
        return "No such alias suggestion exists in the database";
    }

}
