package de.itermori.pse.kitroomfinder.backend.exceptions;

/**
 * Exception class for handling case when no alias with the provided name and mapID exists.
 *
 * @author Adriano Castro
 * @version 1.0
 */
public class NoSuchAliasException extends RuntimeException {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return "No such alias exists in the database";
    }

}
