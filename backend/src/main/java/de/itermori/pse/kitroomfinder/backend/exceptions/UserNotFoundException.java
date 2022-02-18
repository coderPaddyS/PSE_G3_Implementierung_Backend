package de.itermori.pse.kitroomfinder.backend.exceptions;

/**
 * Exception class for handling case when provided user is not registered yet.
 */
public class UserNotFoundException extends RuntimeException{

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return "User not registered yet";
    }

}
