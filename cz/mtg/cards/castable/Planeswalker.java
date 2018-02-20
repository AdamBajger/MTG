package cz.mtg.cards.castable;

import cz.mtg.exceptions.NegativeNotAllowedException;
import cz.mtg.exceptions.RestrictedCounterAmountException;
import cz.mtg.game.CounterType;
import cz.mtg.game.targets.AttackableTarget;

public interface Planeswalker extends AttackableTarget, CastableCard {
    @Override
    default void defaultTakeDamage(int damage) {
        if (damage < 0) throw new NegativeNotAllowedException("Damage taken MUST be positive value!");
        try {
            removeCounters(CounterType.LOYALTY, damage);
        } catch (RestrictedCounterAmountException e) {
            //I we got here, it means that the damage was greater than Planeswalker's loyalty counters amount
            // thus the planeswalker died
            //e.printStackTrace(); --> no need for printing the stacktrace
            try {
                removeCounters(CounterType.LOYALTY, getCounterAmount(CounterType.LOYALTY));
            } catch (RestrictedCounterAmountException ee) {
                // if we get here, something terrible happened
                ee.printStackTrace();
                System.err.println("Tried to remove the same amount of counters the planeswalker already had," +
                        " but it was too much!");
            }
        }
    }
}
