package cz.mtg.testCards;

import cz.mtg.abilities.IndestructibleAbility;
import cz.mtg.abilities.InfectAbility;
import cz.mtg.cards.Card;
import cz.mtg.cards.castable.Artifact;
import cz.mtg.cards.castable.creature.AbstractCreatureCard;
import cz.mtg.game.Color;
import cz.mtg.game.Player;
import cz.mtg.game.mana.BasicMana;
import cz.mtg.game.mana.ManaSet;

import java.util.Collections;

/**
 * Blightsteel Colossus
 */
public class BlightsteelColossus extends AbstractCreatureCard implements Artifact {
    /**
     * Creates a well known card Blightsteel Colossus
     */
    public BlightsteelColossus(Player owner) {
        super("Blightsteel Colossus", owner, new ManaSet(Collections.singletonList(new BasicMana(12, null, Color.COLORLESS))), 11, 11);
        addAbility(new InfectAbility(this));
        addAbility(new IndestructibleAbility(this));
    }

    @Override
    public boolean defaultHasSummoningSickness() {
        return false;
    }

}
