package cz.mtg.exceptions;

/**
 * This exception is thrown when you try to tap or untap already tapped or untapped exception
 */
public class AlreadyTappedOrUntappedException extends Exception {
    /**
     * Warns you that you try to tap or untap already tapped or untapped card
     * @param tapped will be provided in the err message as information
     */
    public AlreadyTappedOrUntappedException(boolean tapped) {
        super(tapped ? "Card is already tapped!" : "Card is already untapped!");
    }
}
