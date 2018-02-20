package cz.mtg.game;

import cz.mtg.exceptions.NegativeNotAllowedException;
import cz.mtg.exceptions.RestrictedCounterAmountException;

/**
 * this class represents counter
 * As in MTG you can put counters on a card
 * Counter has a type (represents as string) and amount (int)
 * Counters can be added and removed, counter can NOT have zero amount, instead of reaching zero, counter is removed
 * Method for adding and removing counters to/from objects are implemented on particular objects
 * This class only implements methods to add or subtract amount of counters, throwing ZeroCountersException exception
 */
public class Counter {
    private int amount;
    private final CounterType counterType;

    /**
     * Creates a counter of given type with given amount
     * @param amount amount of counters
     * @param counterType type of the counter
     */
    public Counter(int amount, CounterType counterType) {
        this.amount = amount;
        this.counterType = counterType;
    }

    public void changeAmount(int amount) throws RestrictedCounterAmountException {
        if(amount == 0) {
            // well, there is no reason to do anything, if you want to add nothing
            return;
        }
        if(this.amount + amount < 0) {
            throw new RestrictedCounterAmountException(
                    "Tried to change counters from " + this.amount + " to "+ (this.amount + amount) + "!");
        }
        // If everything is fine, change the value
        this.amount += amount;
    }

    private void checkNotNegative(int amount) throws NegativeNotAllowedException {
        if(amount < 0) {
            throw new NegativeNotAllowedException("Tried to add/remove " + amount + " counters.");
        }
    }

    /**
     * Increases the value of this counter by given amount
     * @param amount given amount
     * @throws NegativeNotAllowedException whenever a negative number is inputted, exception is thrown
     *
     * use the changeAmount() method
     */
    @Deprecated
    public void addAmount(int amount) throws NegativeNotAllowedException {
        checkNotNegative(amount);
        try {
            changeAmount(amount);
        } catch (RestrictedCounterAmountException e) {
            // because of the checkNotNegative() method, we can never get there
            // but if we still do, something very strange is happening
            e.printStackTrace();
        }
    }

    /**
     *
     * @param amount no reason
     * @throws RestrictedCounterAmountException no reason
     * @throws NegativeNotAllowedException no reason
     * use the changeAmount() method
     */
    @Deprecated
    public void removeAmount(int amount) throws RestrictedCounterAmountException, NegativeNotAllowedException {
        checkNotNegative(amount);
        changeAmount(-amount);
    }

    @Override
    public String toString() {
        return "Counter{" + amount + " " + counterType.toString().toLowerCase() + " counters" + '}';
    }

    public int getAmount() {
        return amount;
    }
}
