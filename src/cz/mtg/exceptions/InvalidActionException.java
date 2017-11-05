package cz.mtg.exceptions;

/**
 * If you have no clue which exception to use, use this.
 * You must always specify what is happening through a String parameter when throwing this exception
 */
public class InvalidActionException extends Exception {
    /**
     * This constructor allows you to specify a message for this exception.
     * @param msg Message to be appended.
     */
    public InvalidActionException(String msg) {
        super("Action is not permitted. " + msg);
    }
}
