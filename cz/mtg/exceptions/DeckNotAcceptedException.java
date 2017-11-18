package cz.mtg.exceptions;

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
}
