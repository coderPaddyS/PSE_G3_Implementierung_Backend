package de.itermori.pse.kitroomfinder.backend.exceptions;

/**
 * Exception class for handling bad tokens.
 *
 * @author Lukas Zetto
 * @version 1.0
 */
public class BadTokenException extends RuntimeException {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return "invalid Token";
    }

}