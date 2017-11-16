package cz.mtg.testCards;

import cz.mtg.cards.lands.AbstractLandCard;
import cz.mtg.game.Color;
import cz.mtg.game.Player;

import java.util.Set;

public class Glimmerpost extends AbstractLandCard {
    public Glimmerpost(Player owner) {
        super("Glimmerpost", owner);
    }

    @Override
    public Set<Color> defaultGetCardColors() {
        return null;
    }
}
