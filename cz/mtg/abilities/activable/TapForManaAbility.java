package cz.mtg.abilities.activable;

import cz.mtg.game.Ability;
import cz.mtg.game.ManaCollection;

/**
 * This interface describes ability of a land to nbe tapped for mana
 */
public abstract class TapForManaAbility extends Ability {
    private String name;
    private String description;
    private ManaCollection cost;



    /**
     * Casual default method to cast a card
     *
     *
     */
    @Override
    public void cast() {

    }
}
