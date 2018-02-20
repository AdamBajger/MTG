package cz.mtg.abilities;

import cz.mtg.abilities.abstracts.Ability;
import cz.mtg.abilities.abstracts.passive.AbilityPassive;
import cz.mtg.abilities.abstracts.passive.AffectsBlockingBy;
import cz.mtg.cards.Card;
import cz.mtg.cards.castable.creature.Creature;

/**
 * This creature can block as if it had flying. I.E. it can block creatures with flying.
 * This is only markup ability.
 * Block validity check is execudet on the side of flying (and similar abilities)
 */
public class Reach extends AbilityPassive {
    /**
     * @see AbilityPassive
     */
    public Reach(Card source) {
        super("reach", "This creature can block as if it had flying.", source);
    }
}
