package cz.mtg.game;

import cz.mtg.exceptions.InvalidActionException;
import cz.mtg.game.mana.Mana;
import cz.mtg.game.mana.ManaSet;

/**
 * This interface is here to wrap around Castable and non-Castable abilities.
 * There are abilities, that require mana, but are not Castable (Tap for Mana ability)
 *
 */
public interface ConsumesMana extends Stackable {

    /**
     * This method returns mana cost of this castable object
     * @return mana needed to cast this spell
     */
    ManaSet getManaCost();

    /**
     * This method takes a mana object and tells you if you can spend it on this spell
     * @param mana mana object tested
     * @return true if mana is usable, false otherwise
     */
    default boolean manaCanBeSpendOnThis(Mana mana) {
        return true;
    }

    /**
     * This method will execute / satisfy the additional costs
     * Everything that is required to cast desired card/spell will be done
     * @throws InvalidActionException if the requirements can not be satisfied
     */
    default void spendAdditiondalManaCost() throws InvalidActionException {}

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
}
