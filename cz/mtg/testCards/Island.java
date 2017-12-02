package cz.mtg.testCards;

import cz.mtg.cards.lands.basic.AbstractBasicLandCard;
import cz.mtg.game.Color;
import cz.mtg.game.Player;

/**
 * Basic land - plains
 */
public class Island extends AbstractBasicLandCard {
    /**
     * Basic constructor
     * @param owner player owning this card
     */
    public Island(Player owner) {
        super("Island", owner, Color.BLUE);
    }
}
