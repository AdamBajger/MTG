package cz.mtg.cards.lands;

import cz.mtg.cards.Card;
import cz.mtg.game.Color;

import java.util.Set;

/**
 * Interface describing general land card
 * Contains overridable methods as in CardInterface
 * @see Card
 *
 * -------------------------------------
 *  TODO:
 *      Rework methods forn mana
 *      Generally the Land card does NOT give you mana, only specific land types have this ability
 *      "tap for mana" should be implemented as a regular ability, so we can have lands, that does not give mana
 */
public interface Land extends Card {

    /**
     * According to rules, land card has no color nor mana cost.
     * @return null for no color
     */
    @Override
    default Set<Color> defaultGetCardColors() {
        return null;
    }
}
