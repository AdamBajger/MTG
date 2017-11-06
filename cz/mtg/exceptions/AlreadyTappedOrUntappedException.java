package cz.mtg.exceptions;

public class AlreadyTappedOrUntappedException extends Exception {
    /**
     * Warns you that you try to tap or untap already tapped or untapped card
     * @param tapped
     */
    public AlreadyTappedOrUntappedException(boolean tapped) {
        super(tapped ? "Card is already tapped!" : "Card is already untapped!");
    }
}
