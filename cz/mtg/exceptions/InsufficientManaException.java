package cz.mtg.exceptions;

public class InsufficientManaException extends Exception {
    /**
     * Throws an exception with a message appended behind the default message "Not enough mana."
     * @param message message to append
     */
    public InsufficientManaException(String message) {
        super("Not enough mana. " + message);
    }

    /**
     * Throws an exception with default message, that not enough mana is present in mana pool
     */
    public InsufficientManaException() {
        super("Not enough mana.");
    }
}
