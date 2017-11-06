package cz.mtg.testCards;

import cz.mtg.abilities.IndestructibleAbility;
import cz.mtg.cards.castable.Artifact;
import cz.mtg.cards.castable.creature.Creature;
import cz.mtg.cards.castable.creature.CreatureCard;
import cz.mtg.exceptions.RestrictedManaAmountException;
import cz.mtg.game.Mana;

import java.util.HashSet;
import java.util.Set;

public class BlightsteelColossus extends CreatureCard implements Creature, Artifact, IndestructibleAbility {
    private Set<Mana> manaCost = new HashSet<Mana>();

    /**
     * Creates a well known card Blightsteel Colossus
     */
    public BlightsteelColossus() throws RestrictedManaAmountException {
        super("Blightsteel Colossus", null, 11, 11);
    }

    @Override
    public boolean defaultHasSummoningSickness() {
        return false;
    }

    @Override
    public boolean defaultCast() {
        return false;
    }

    @Override
    public boolean resolve() {
        return false;
    }
}
