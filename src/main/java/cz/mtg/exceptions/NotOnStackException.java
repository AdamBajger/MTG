package cz.mtg.exceptions;

public class NotOnStackException extends Exception {
    public static final String defaultMessage = "Spell is not on stack.";
    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public NotOnStackException() {
        super(defaultMessage);
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public NotOnStackException(String message) {
        super(defaultMessage + " " + message);
    }
}
