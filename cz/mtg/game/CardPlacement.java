package cz.mtg.game;

/**
 * This enum contains game locations that a card can lie in
 * You can move a card to all of these locations under some circumstances
 */
public enum CardPlacement {
    SIDEBOARD, LIBRARY, HAND, STACK, BATTLEFIELD, GRAVEYARD, EXILE, COMMAND_ZONE;
}
