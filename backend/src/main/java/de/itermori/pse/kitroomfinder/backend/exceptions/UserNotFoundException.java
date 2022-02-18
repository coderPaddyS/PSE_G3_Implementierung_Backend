package de.itermori.pse.kitroomfinder.backend.exceptions;

/**
 * Exception class for handling case when provided user is not registered yet.
 *
 * @author Lukas Zetto
 * @version 1.0
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
