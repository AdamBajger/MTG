
package cz.mtg.game;

import cz.mtg.cards.Card;
import cz.mtg.exceptions.InsufficientManaException;
import cz.mtg.exceptions.InvalidActionException;

import java.util.Set;

/**
 * This interface describes how an object placeable on stack should look and which methods to implement
 * This Interface is crucial to the MTG game core, because it describes how Cards and Abilities are cast
 */
public interface Stackable {
    /**
     * This method returns the source Card causing this spell to go on stack
     * @return source Card of the spell
     */
    Card getSource();

    /**
     * By default this card returns the controller of card causing this spell to go on stack
     * Basically the caster of this spell
     * @return Casting Player
     */
    default Player getCastingPlayer() {
        return getSource().getController();
    }

    /**
     * This method returns mana cost of this castable object
     * @return mana needed to cast this spell
     */
    Set<Mana> getManCost();

    /**
     * This method checks if a given player has enough mana to cast this
     * The casting player is always the owner of the casted card or owner of the casting source
     * @return True if there is enough mana. False otherwise
     */
    default boolean enoughMana() {
        ManaCollection checkedManaPool = getSource().getController().getManaPool();
        Set<Mana> manaCost = getManCost();
        // for each mana color in manaCost, check if there is enough mana of that color in checkedManaPool
        for(Mana m : manaCost) {
            if(!(checkedManaPool.getManaOfColorAmount(m.getColor()) >= m.getAmount())) {
                return false;
            }
        }
        // no insufficient mana found ---> return true
        return true;
    }

    /**
     * This method checks if additional costs can be satisfied.
     * By default there are no conditions, thus all conditions are met (default return is True)
     *  For example for card Harrow, it checks if there is a land on battlefield, that could be sacrificed
     *  as an additional cost to cast Harrow.
     * @return True if conditions are met, False otherwise
     */
    default boolean additionalCastConditionsCheck() {
        return true;
    }

    /**
     * This will return the conditions that have to be satisfied in a human readable manner
     * For example for Harrow, it would return "As an additional cost to cast Harrow, sacrifice a land."
     * @return Text-written conditions for casting this spell
     */
    String getEffectDescription();

    /**
     * Casual default method to cast a card
     * it just puts a spell on stack
     */
    default void defaultCast() {
        getSource().getController().getGameAssigned().getSpellStack().put(this);
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
     *      Then, the land gets destroyed and an event is fired and so on...
     *
     * --------------------
     *  TODO:
     *      Method signature incomplete, more Exceptions expected to be thrown
     *      --> Those exception do not exist yet, but should be introduced
     *      --> AdditionalCostException...
     *
     */
    default void cast(Stack currentStack) throws InsufficientManaException, InvalidActionException {
        if(!enoughMana()) {
            // not enough mana
            throw new InsufficientManaException();
        }

        if(!additionalCastConditionsCheck()) {
            // additional conditions not met
            throw new InvalidActionException(getEffectDescription());
        }

        // if everything is ok, put it on stack
        defaultCast();
    }

    /**
     * This method contains what happens when a card or ability resolves.
     * That means, what happens if the card is on the TOP of the STACK and every player passed its priority
     * without doing anything.
     * For most Cards (excepts Instants or Sorceries) it means just putting the card on the battlefield
     * but for abilities and, earlier excluded, instants and sorceries, it means the whole card functionality
     */
    void resolve();
}
