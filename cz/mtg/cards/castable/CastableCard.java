package cz.mtg.cards.castable;

import cz.mtg.cards.Card;
import cz.mtg.game.Color;
import cz.mtg.game.Stackable;

import java.util.Set;

/**
 * Tells you which methods a castable card should have
 * also serves as a container for overridable methods in the same ways as {@link Card CardInterface} does
 */
public interface CastableCard extends Card, Stackable {

}
