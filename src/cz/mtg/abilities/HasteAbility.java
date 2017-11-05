package cz.mtg.abilities;

import cz.mtg.cards.castable.creature.Creature;

/**
 * Implementation of a Haste ability
 */
public interface HasteAbility extends Creature {
    /**
     * Here you stat that if you want to see if a creature implementing HasteAbility
     * has summoning sickness, it will always return false
     * @return False
     */
    @Override
    default boolean hasSummoningSickness() {
        return false;
    }
}
