package cz.mtg.testCards;

import cz.mtg.abilities.IndestructibleAbility;
import cz.mtg.abilities.InfectAbility;
import cz.mtg.cards.castable.Artifact;
import cz.mtg.cards.castable.creature.Creature;
import cz.mtg.cards.castable.creature.AbstractCreatureCard;
import cz.mtg.exceptions.RestrictedManaAmountException;
import cz.mtg.game.Color;
import cz.mtg.game.Mana;
import cz.mtg.game.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Blightsteel Colossus
 */
public class BlightsteelColossus extends AbstractCreatureCard implements Artifact {
    /**
     * Creates a well known card Blightsteel Colossus
     */
    public BlightsteelColossus(Player owner) {
        super("Blightsteel Colossus", owner, new HashSet<>(Collections.singletonList(new Mana(Color.COLORLESS, 12))), 11, 11);
        addAbility(new InfectAbility(this));
        addAbility(new IndestructibleAbility(this));
    }

    @Override
    public boolean defaultHasSummoningSickness() {
        return false;
    }

}
