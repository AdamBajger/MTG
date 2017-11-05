package cz.mtg.cards.lands;

import cz.mtg.cards.CardInterface;
import cz.mtg.game.Color;
import cz.mtg.game.Mana;

import java.util.Set;

/**
 * Interface describing general land card
 * Contains overridable methods as in CardInterface
 * @see CardInterface
 */
public interface Land extends CardInterface {
    /**
     * Returns a Set of Mana objects representing the mana this land gives
     * @return given mana Set
     */
    int[] defaultGenerateMana();

    /**
     * Overridable method for getting mana from the land (basically "tap for mana" action)
     * @return array of ints containing info about mana generated
     */
    default int[] generateMana() {
        return defaultGenerateMana();
    }

    /**
     * According to rules, landcard has no color nor mana cost.
     * @return null for no color
     */
    @Override
    default Set<Color> defaultGetCardColors() {
        return null;
    }
}
