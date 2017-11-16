package cz.mtg.testCards;

import cz.mtg.abilities.TapForManaAbility;
import cz.mtg.cards.lands.AbstractLandCard;
import cz.mtg.game.Color;
import cz.mtg.game.Player;

import java.util.Set;

/**
 * Basic land - plains
 */
public class Plains extends AbstractLandCard {
    /**
     * Basic constructor
     * @param owner player owning this card
     */
    public Plains(Player owner) {
        super("Plains", owner);
        addAbility(new TapForManaAbility());
    }

    @Override
    public Set<Color> defaultGetCardColors() {
        return null;
    }
}
