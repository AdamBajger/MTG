
package cz.mtg.game;

import cz.mtg.exceptions.InsufficientManaException;
import cz.mtg.exceptions.InvalidActionException;

/**
 * This interface describes how an object placeable on stack should look and which methods to implement
 * This Interface is crucial to the MTG game core, because it describes how Cards and Abilities are cast
 */
public interface Castable extends ConsumesMana {



    /**
     * Casual default method to cast a card
     * it just puts a spell on stack
     */
    default void defaultCast() {
        getController().getGameAssigned().getSpellStack().put(this);
    }

    /**
     * Overridable method for casting a card
     * Will cast the card
     * Checks all conditions --> enough mana, additional costs (e. g. card Harrow)
     * if everything is OK, it will put card onto stack
     *
     *  IDEA:
     *      In this method you will use commandline input (for example) to specify
     *      targets for additional mana cost.
     *      Let's have a card Harrow:
     *      You have to sacrifice a land card as an additional cost to cast Harrow.
     *      So you will get indexed land cards on the battlefield and you will write a number, by which you will
     *      choose which land to sacrifice, easy enough :D
     *      Then, the land gets destroyed and an event is fired (if we ever implement events) and so on...
     *
     *
     */
    default void cast() throws InsufficientManaException, InvalidActionException {
        if(getController().notEnoughMana(this)) {
            // not enough mana
            throw new InsufficientManaException("Not enough mana.");
        }

        if(!additionalCastConditionsCheck()) {
            // additional conditions not met
            throw new InvalidActionException(getEffectDescription());
        }

        // if everything is ok, put it on stack
        defaultCast();
    }

}
