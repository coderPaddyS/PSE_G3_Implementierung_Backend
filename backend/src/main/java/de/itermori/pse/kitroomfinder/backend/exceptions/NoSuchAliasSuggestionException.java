package de.itermori.pse.kitroomfinder.backend.exceptions;

/**
 * Exception class for handling case when no alias suggestion with provided name exists.
 *
 * @author Adriano Castro
 * @version 1.0
 */
public class NoSuchAliasSuggestionException extends RuntimeException {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return "No such alias suggestion exists in the database";
    }

}
