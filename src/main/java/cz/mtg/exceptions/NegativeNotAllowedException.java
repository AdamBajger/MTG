package cz.mtg.exceptions;

/**
 * This exception is thrown whenever anyone tries to input zero or negative value, wherever it is NOT ALLOWED
 * like addMana(), putCounter() and so on. Mana can be only spent, counter removed, but only with appropriate method
 * --> split those methods to "add" and "remove" methods
 *
 */
public class NegativeNotAllowedException extends RuntimeException {
    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public NegativeNotAllowedException() {
        super("Negative value not allowed.");
    }

    /**
     * Constructs a new exception with the specified detail message appended behind the defauzlt one.
     * The cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public NegativeNotAllowedException(String message) {
        super("Negative value not allowed. " + message);
    }
}
