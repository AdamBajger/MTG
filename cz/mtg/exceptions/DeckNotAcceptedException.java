package cz.mtg.exceptions;

import java.security.PrivilegedActionException;

/**
 * This exception is thrown when your deck was not accepted for ceratain reason/s
 * Reasons may be following:
 *  not enough cards for certain game format
 *  more cards of the same name than allowed
 */
public class DeckNotAcceptedException extends Exception {
    /**
     * Throws new exception telling you why it was thrown.
     * @param whyNotAccepted Tells you why your deck was not accepted.
     */
    public DeckNotAcceptedException(String whyNotAccepted) {
        super(whyNotAccepted);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of <tt>(cause==null ? null : cause.toString())</tt> (which
     * typically contains the class and detail message of <tt>cause</tt>).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables (for example, {@link
     * PrivilegedActionException}).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.4
     */
    public DeckNotAcceptedException(Throwable cause) {
        super(cause);
    }
}
