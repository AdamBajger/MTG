package cz.mtg.abilities.abstracts.active;

import cz.mtg.abilities.abstracts.Ability;
import cz.mtg.cards.Card;
import cz.mtg.game.mana.ManaPool;
import cz.mtg.game.Castable;

/**
 * This class contains one ability, that can be activated. Its effects, conditions and everything
 * I forgot what the implementation should be about, so ...
 * i will come back to this later, I have to time now
 */
public abstract class AbilityCastable extends Ability implements Castable {

    private final ManaPool cost;

    /**
     * Constructs a castable activable ability, with activation cost.
     *
     * @param keyWord keyword assigned to this ability, use the other constructor or input "" (blank string)
     *                if there is no such keyword
     * @param effectDescription description string telling you what effects and functionalities
     *                          this ability has.
     * @param source source card containing this ability instance
     * @param cost how much it costs to activate this ability
     */
    public AbilityCastable(String keyWord, String effectDescription, Card source, ManaPool cost) {
        super(keyWord, effectDescription, source);
        this.cost = cost;
    }

    /**
     * I will not repeat myself...
     * @see AbilityCastable#AbilityCastable(String, String, Card, ManaPool)
     */
    public AbilityCastable(String effectDescription, Card source, ManaPool cost) {
        super(effectDescription, source);
        this.cost = cost;
    }
}
