package cz.mtg.testCards;


import cz.mtg.abilities.BasicTapForManaAbility;
import cz.mtg.cards.lands.AbstractLandCard;
import cz.mtg.game.Color;
import cz.mtg.game.Player;
import cz.mtg.game.mana.BasicMana;
import cz.mtg.game.mana.Mana;
import cz.mtg.game.mana.ManaSet;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static cz.mtg.game.Color.*;

public class Glimmerpost extends AbstractLandCard {
    public Glimmerpost(Player owner) {
        super("Glimmerpost", owner);
        addAbility(new BasicTapForManaAbility(
                        this,
                        new ManaSet(),
                        new ManaSet(new HashSet<Mana>(Collections.singleton(new BasicMana(this, COLORLESS))))
                ));
    }

    @Override
    public Set<Color> defaultGetCardColors() {
        return null;
    }
}
