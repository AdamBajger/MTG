package cz.mtg.cards.castable;

import cz.mtg.cards.CardInterface;
import cz.mtg.game.Color;
import cz.mtg.game.Stackable;

import java.util.Set;

/**
 * Tells you which methods a castable card should have
 * also serves as a container for overridable methods in the same ways as {@link CardInterface CardInterface} does
 */
public interface CastableCardInterface extends CardInterface, Stackable {

    /**
     * Casual default method to return set of colors this card inherits
     * @return Set of colors
     */
    Set<Color> defaultGetCardColors();

    /**
     * This is the overridable method to get you colors of a card
     * Reason for this split is for example ability called "devoid", which tells you that a card is colorless
     * independently on its mana cost colors
     * @return Set of colors
     */
    default Set<Color> getCardColors() {
        return defaultGetCardColors();
    }



}
