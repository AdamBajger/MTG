package cz.mtg.abilities;

import cz.mtg.cards.castable.Planeswalker;
import cz.mtg.cards.castable.creature.Creature;
import cz.mtg.exceptions.NegativeNotAllowedException;
import cz.mtg.game.CounterType;
import cz.mtg.game.Player;
import cz.mtg.game.targets.DamageableTarget;

/**
 * This interface implements an infect ability
 * ------------------------------------
 *  TODO:
 *      Well this one is HARD one
 *      We haven't even implemented attack/damage system, so it is too soon to implement this
 *      But remember, that this i
 */
public interface InfectAbility extends Creature {
    /**
     * This method overrides the default Creature interface method to deal damage
     * Instead of dealing damage it puts counters on the creature
     * @param damageableTarget target to be dealt damage
     * @param damageAmount amount of -1/-1 counters to be put
     */
    @Override
    default void dealDamage(DamageableTarget damageableTarget, int damageAmount) throws NegativeNotAllowedException {
        if(damageableTarget instanceof Player) {
            damageableTarget.putCounters(CounterType.POISON, damageAmount);
        } else if(damageableTarget instanceof Planeswalker) {
            damageableTarget.takeDamage(damageAmount);
        } else if(damageableTarget instanceof Creature) {
            putCounters(CounterType.P_T_COUNTER_NEGATIVE, damageAmount);
        }
    }
}
