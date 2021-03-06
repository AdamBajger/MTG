package cz.mtg.game.mana;

import cz.mtg.cards.Card;
import cz.mtg.game.Color;
import cz.mtg.game.ConsumesMana;

/**
 * This class represents blue mana as it is
 * Just a basic blue mana generated by islands
 */
public class BasicMana extends Mana implements Cloneable {

    /**
     * Constructs a blue mana unit with amount 1
     *
     */
    public BasicMana(Card source, Color c) {
        super(c, 1, source);
    }

    /**
     * Constructs a mana unit with blue color and desired amount
     *
     * @param amount desired amount
     * @param source source card generating this mana
     */
    public BasicMana(int amount, Card source, Color c) {
        super(c, amount, source);
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
    public boolean canBeSpentOn(ConsumesMana castSpell) {
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        return super.clone();
    }
}
