package cz.mtg.game.targets;

import cz.mtg.exceptions.NegativeNotAllowedException;

/**
 * Well this interface is here to recognize targets that can take damage in any way.
 * Every damageable target should implement methods described here
 * So far there are there things that can take damage:
 *  Player
 *  Creature
 *  Planeswalker
 * Each of these has its own way of taking damage, but this interface tells them that they must
 * have such methods
 *
 * This interface does not handle different ways of taking damage (as seen in Wither or Infect abilities),
 * this interface only implements scheme for default methods of taking normal damage.
 * It is mostly abstract and serves only as a helper for recognizing what can be damaged and what not
 *
 */
public interface DamageableTarget {
    /**
     * This is the default casual method to take damage
     * Every damageable target must implement such method
     * Damage taken MUST me positive
     * @param damage amount of damage taken
     */
    void defaultTakeDamage(int damage);

    /**
     * This is the overridable method for taking damage
     * If no ability (e.g. Infect, Wither) overrides this, it uses default method for taking damage
     * @param damage amount of damage taken
     */
    default void takeDamage(int damage) {
        defaultTakeDamage(damage);
    }
}
