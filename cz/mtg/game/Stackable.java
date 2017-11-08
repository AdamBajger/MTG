
package cz.mtg.game;

import cz.mtg.exceptions.InsufficientManaException;
import cz.mtg.exceptions.InvalidActionException;

/**
 * This interface describes how an object placeable on stack should look and which methods to implement
 * This Interface is crucial to the MTG game core, because it describes how Cards and Abilities are cast
 * @author
 */
public interface Stackable {
    /**
     * This method returns mana cost of this castable object
     * @return mana cost
     */
    ManaCollection getManCost();

    /**
     * This method checks if additional costs can be satisfied.
     * By default there are no conditions, thus all conditions are met (default return is True)
     *  For example for card Harrow, it checks if there is a land on battlefield, that could be sacrificed
     *  as an additional cost to cast Harrow.
     * @return True if conditions are met, False otherwise
     */
    default boolean castConditionsCheck() {
        return true;
    }

    /**
     * This will return the conditions that have to be satisfied in a human readable manner
     * For example for Harrow, it would return "As an additional cost to cast Harrow, sacrifice a land."
     * @return Text-written conditions for casting this spell
     */
    String getCastConditionsMessage();

    /**
     * Casual default method to cast a card
     * @return True if card successfully went to Stack, False otherwise
     */
    void defaultCast() throws InsufficientManaException;

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
     *      Then, the land gets destroyed and an event is fired and so on...
     *
     * --------------------
     *  TODO:
     *      Method signature incomplete, more Exceptions expected to be thrown
     *      --> Those exception do not exist yet, but should be introduced
     *      --> AdditionalCostException...
     *
     * @return True if card successfully went to Stack, False otherwise
     */
    default void cast() throws InsufficientManaException, InvalidActionException {
        if(castConditionsCheck()) {
            defaultCast();
        } else {

        }
    }

    /**
     * This method contains what happens when a card or ability resolves.
     * That means, what happens if the card is on the TOP of the STACK and every player passed its priority
     * without doing anything.
     */
    boolean resolve();
}
