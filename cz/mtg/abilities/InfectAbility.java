package cz.mtg.abilities;

import cz.mtg.abilities.abstracts.passive.AbilityPassive;
import cz.mtg.abilities.abstracts.passive.AffectsDamageDealing;
import cz.mtg.cards.Card;
import cz.mtg.cards.castable.Planeswalker;
import cz.mtg.cards.castable.creature.Creature;
import cz.mtg.exceptions.ProtectionFromSourceException;
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
public class InfectAbility extends AbilityPassive implements AffectsDamageDealing {

    public InfectAbility(Card source) {
        super("infect", "This creature deals damage to creatures in the form of -1/-1 counters" +
                " and to players in the form of poison counters. ", source);
    }

    @Override
    public void dealDamage(DamageableTarget target) throws ProtectionFromSourceException {

        if(target instanceof Player) {

            // give poison counters to Player
            ((Player)target).changeCounterAmountByValue(CounterType.POISON, ((Creature) getSourceCard()).getActualPower());
        } else if(target instanceof Creature) {
            // put -1/-1 counters to creatures
            ((Creature)target).putCounters(CounterType.P_T_COUNTER_NEGATIVE, ((Creature) getSourceCard()).getActualPower());
        } else if(target instanceof Planeswalker) {
            target.takeDamage(((Creature) getSourceCard()).getActualPower(), getSourceCard());

        }
    }
}
