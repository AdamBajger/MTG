package cz.mtg.testCards;

import cz.mtg.abilities.IndestructibleAbility;
import cz.mtg.abilities.InfectAbility;
import cz.mtg.cards.castable.Artifact;
import cz.mtg.cards.castable.creature.Creature;
import cz.mtg.cards.castable.creature.CreatureCard;
import cz.mtg.exceptions.RestrictedManaAmountException;
import cz.mtg.game.Color;
import cz.mtg.game.Mana;
import cz.mtg.game.ManaCollection;
import cz.mtg.game.Player;

import java.util.HashSet;
import java.util.Set;

public class BlightsteelColossus extends CreatureCard implements Creature, Artifact, IndestructibleAbility, InfectAbility {


    /**
     * Creates a well known card Blightsteel Colossus
     */
    public BlightsteelColossus(Player owner) throws RestrictedManaAmountException {
        super(
                "Blightsteel Colossus",
                owner,
                new ManaCollection(new Mana[]{new Mana(Color.COLORLESS, 12)}),
                11,
                11
        );
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
