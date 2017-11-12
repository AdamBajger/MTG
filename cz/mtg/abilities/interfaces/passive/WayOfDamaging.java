package cz.mtg.abilities.interfaces.passive;


import cz.mtg.cards.Card;
import cz.mtg.game.targets.DamageableTarget;

/**
 * This interface must be implemented by every ability that somehow changes the manner in which attack is performed
 * Otherwise the ability will be ommited
 *  For example:
 *      infect, defender, wither,
 *
 */
public interface WayOfDamaging {
    /**
     * This is the alternative damaging method, that will be used
     * whenever a creature with thgis ability will deal damage
     * @param target target to be dealt damage
     */
    void dealDamage(DamageableTarget target);
}
