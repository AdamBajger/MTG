package cz.mtg.game.mana;

import cz.mtg.cards.Card;
import cz.mtg.exceptions.InvalidActionException;
import cz.mtg.game.Color;
import cz.mtg.game.ConsumesMana;

/**
 * This mana is used as a model, it has no source and does not need it
 * It is used to describe mana costs and abilities and so on
 */
public class RepresentativeMana extends Mana {

    /**
     * Constructs a mana unit with desired color and desired amount
     * The conditions needed to allow spending this mana are none, so spendConditionDescription attribute
     * is set to empty String
     *
     * @param c      desired color
     * @param amount desired amount
     */
    public RepresentativeMana(Color c, int amount) {
        super(c, amount, null);
    }

    /**
     * Checks if this mana can be spend on any Castable spell (i. e. attribute spendableOn = Castable.class)
     * If not, it checks whether the castSpell is instance of the class this mana is supposed to be spent on
     * and if it IS, it returns true, if it is NOT, it returns false.
     *
     * @param castSpell spell being cast
     * @return True/False
     */
    @Override
    public boolean canBeSpentOn(ConsumesMana castSpell) throws InvalidActionException {
        throw new InvalidActionException("Representative mana cannot be checked for spending!");
    }
}
