package cz.mtg.game;

import cz.mtg.cards.Card;
import cz.mtg.cards.castable.Artifact;
import cz.mtg.cards.castable.Enchantment;
import cz.mtg.cards.castable.Planeswalker;
import cz.mtg.cards.castable.creature.Creature;
import cz.mtg.cards.lands.Land;

/**
 * This class serves as a static condition checker for cards
 *  e. g.:
 *      If a card is Creature, or if it is only a Creature card
 *      if a card is permanent, or only a permanent card
 *      if
 * ----------------------------------------
 *  TODO: implement checks for:
 *      Creature
 *      Creature card
 *      Artifact
 *      Artifact card
 *      Enchantment
 *      Enchantment card
 *      Land
 *      Land card
 *      Sorcery card
 *      Instant card
 */
public class ConditionChecker {
    /**
     * Tells you whether a card is or is not permanent
     * Card is a permanent only if it is a permanent card and is in play
     * Cards in graveyards, exile, hand, library, etc... are NOT "permanents", BUT may still be "permanent cards"
     * There is difference between "Permanents" and "Permanent cards"
     * @return True if a card is permanent, False otherwise
     */
    public static boolean isPermanent(Card card) {
        return isPermanentCard(card) && card.getCardPlacement() == CardPlacement.BATTLEFIELD;
    }

    /**
     * Returns if this card is permanent card
     * --> that means just to check for it is instance of Creature, Artifact, Enchantment or ?Planeswalker?
     * @return true if card is permanent card, false otherwise
     */
    public static boolean isPermanentCard(Card card) {
        return (card instanceof Land
                || card instanceof Creature
                || card instanceof Artifact
                || card instanceof Enchantment
                || card instanceof Planeswalker); // TODO: check is planeswalker is permanent
    }
}
