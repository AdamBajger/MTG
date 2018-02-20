package cz.mtg.testCards;

import cz.mtg.abilities.Reach;
import cz.mtg.cards.castable.creature.AbstractCreatureCard;
import cz.mtg.game.Color;
import cz.mtg.game.Player;
import cz.mtg.game.mana.BasicMana;
import cz.mtg.game.mana.Mana;
import cz.mtg.game.mana.ManaSet;
import cz.mtg.game.mana.RepresentativeMana;

import java.util.Arrays;
import java.util.HashSet;


public class SporecapSpider extends AbstractCreatureCard {
    public SporecapSpider(Player owner) {
        super(
                "Sporecap Spider",
                owner,
                new ManaSet(
                        new HashSet<>(
                                Arrays.asList(
                                        new RepresentativeMana(Color.COLORLESS, 2),
                                        new RepresentativeMana(Color.GREEN, 1)
                                )
                        )
                ),
                1,
                5
        );
        addAbility(new Reach(this));
    }
}
