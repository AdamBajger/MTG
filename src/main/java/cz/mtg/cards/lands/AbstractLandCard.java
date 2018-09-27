package cz.mtg.cards.lands;

import cz.mtg.cards.AbstractCard;
import cz.mtg.cards.Card;
import cz.mtg.game.Player;

/**
 * Incomplete!!!
 */
public abstract class AbstractLandCard extends AbstractCard implements Land {

    public AbstractLandCard(String name, Player owner) {
        super(name, owner);
    }
}
