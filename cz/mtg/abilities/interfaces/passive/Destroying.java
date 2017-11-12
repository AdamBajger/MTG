package cz.mtg.abilities.interfaces.passive;

import cz.mtg.cards.Card;

/**
 * This interface is implemented by abilities that somehow modify the destroy action
 *  For example:
 *      Indestructible, Persist
 */
public interface Destroying {
    /**
     * This is an alternative method for destroying a creature
     * It is called instead of the default {@link Card#destroy() destroy} method introduced in {@link Card card} interface
     */
    void destroy() throws Exception;
}
