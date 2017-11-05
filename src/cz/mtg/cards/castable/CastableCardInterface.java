package cz.mtg.cards.castable;

import cz.mtg.cards.CardInterface;
import cz.mtg.exceptions.InsufficientManaException;
import cz.mtg.game.Color;
import cz.mtg.game.Mana;
import cz.mtg.game.Stackable;

import java.util.Set;

/**
 * Tells you which methods a castable card should have
 * also serves as a container for overridable methods in the same ways as {@link CardInterface CardInterface} does
 */
public interface CastableCardInterface extends CardInterface, Stackable {





}
