package cz.mtg.game;

import cz.mtg.cards.Card;
import cz.mtg.game.mana.Mana;
import cz.mtg.game.mana.ManaSet;

/**
 * This interface is here to wrap around Stackable and non-Stackable abilities.
 * There are abilities, that require mana, but are not Stackable (Tap for Mana ability)
 *
 */
public interface ConsumesMana {
    /**
     * This method returns the source Card causing this spell to go on stack
     * @return source Card of the spell
     */
    Card getSource();

    /**
     * This method returns mana cost of this castable object
     * @return mana needed to cast this spell
     */
    ManaSet getManaCost();

    /**
     * By default this card returns the controller of card causing this spell to go on stack
     * Basically the caster of this spell
     * @return Casting Player
     */
    default Player getCastingPlayer() {
        return getSource().getController();
    }

    default boolean manaCanBeSpendOnThis(Mana mana) {
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


}
