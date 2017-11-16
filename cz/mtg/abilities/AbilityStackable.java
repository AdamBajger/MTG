package cz.mtg.abilities;

import cz.mtg.cards.Card;
import cz.mtg.exceptions.InsufficientManaException;
import cz.mtg.game.ManaCollection;
import cz.mtg.game.Stackable;

/**
 * This class contains one ability, that can be activated. Its effects, conditions and everything
 * I forgot what the implementation should be about, so ...
 * i will come back to this later, I have to time now
 */
public abstract class AbilityStackable extends Ability implements Stackable {

    private final ManaCollection cost;

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
    public AbilityStackable(String keyWord, String effectDescription, Card source, ManaCollection cost) {
        super(keyWord, effectDescription, source);
        this.cost = cost;
    }

    /**
     * I will not repeat myself...
     * @see AbilityStackable#AbilityStackable(String, String, Card, ManaCollection)
     */
    public AbilityStackable(String effectDescription, Card source, ManaCollection cost) {
        super(effectDescription, source);
        this.cost = cost;
    }
}
