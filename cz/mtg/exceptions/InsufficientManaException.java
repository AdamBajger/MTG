package cz.mtg.exceptions;

import cz.mtg.game.mana.Mana;

/**
 * This exception is thrown whenever a Player wants to cast a spell or ability, but has not enough mana
 */
public class InsufficientManaException extends Exception {
    private Mana troubleMakingMana = null;

    /**
     * Throws an exception with a message appended behind the default message "Not enough mana."
     * @param message message to append
     */
    public InsufficientManaException(String message) {
        super("Not enough mana. " + message);
    }

    /**
     * Throws an exception with a message appended behind the default message "Not enough mana."
     * @param message message to append
     */
    public InsufficientManaException(String message, Mana troubleMakingMana) {
        super("Not enough mana. " + message);
        this.troubleMakingMana = troubleMakingMana;
    }


    /**
     * Throws an exception with default message, that not enough mana is present in mana pool
     */
    public InsufficientManaException(Mana troubleMakingMana) {
        super("Not enough mana.");
        this.troubleMakingMana = troubleMakingMana;
    }


    public Mana getTroubleMakingMana() {
        return troubleMakingMana;
    }

}
