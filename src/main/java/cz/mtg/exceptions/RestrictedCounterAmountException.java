package cz.mtg.exceptions;

/**
 * Throw this when working with counters and someomne attempts to decrease value of counters below zero
 */
public class RestrictedCounterAmountException extends RuntimeException {
    private static final String defaultMessage = "Counters can't reach negative values!";
    /**
     * Propagates custom message to the exception
     * @param msg the message
     */
    public RestrictedCounterAmountException(String msg) {
        super(defaultMessage + " " + msg);
    }

    /**
     * Default exception throw with default message
     */
    public RestrictedCounterAmountException() {
        super(defaultMessage);
    }
}
