package cz.mtg.exceptions;

/**
 * Throw this when working with mana and someomne attempts to decrease value of mana below zero
 */
public class RestrictedManaAmountException extends Exception {
    /**
     * Propagates custom message to the exception
     * @param msg the message
     */
    public RestrictedManaAmountException(String msg) {
        super(msg);
    }

    /**
     * Default exception throw with default message
     */
    public RestrictedManaAmountException() {
        super("Mana can't be ZERO or NEGATIVE there!");
    }
}
