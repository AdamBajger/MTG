package cz.mtg.abilities;

import cz.mtg.abilities.interfaces.passive.WayOfDamaging;
import cz.mtg.cards.Card;
import cz.mtg.cards.castable.Planeswalker;
import cz.mtg.cards.castable.creature.Creature;
import cz.mtg.game.CounterType;
import cz.mtg.game.Player;
import cz.mtg.game.targets.DamageableTarget;

/**
 * This interface implements an infect ability
 * Only a creature can have this ability
 * ------------------------------------
 *  TODO:
 *      Well this one is HARD one
 *      We haven't even implemented attack/damage system, so it is too soon to implement this
 *      But remember, that this i
 */
public class InfectAbility extends AbilityPassive implements WayOfDamaging {

    @Override
    public void dealDamage(DamageableTarget target) {
        if(target instanceof Player) {
            // give poison counters to Player
            ((Player)target).changeCounterAmountByValue(CounterType.POISON, ((Creature)getSource()).getActualPower());
        } else if(target instanceof Creature) {
            // put -1/-1 counters to creatures
            ((Creature)target).putCounters(CounterType.P_T_COUNTER_NEGATIVE, ((Creature)getSource()).getActualPower());
        } else if(target instanceof Planeswalker) {
            ((Planeswalker)target).takeDamage(((Creature)getSource()).getActualPower());

        }
    }
}
