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
    private CounterType counterType;

    /**
     * Creates a counter of given type with given amount
     * @param amount amount of counters
     * @param counterType type of the counter
     */
    public Counter(int amount, CounterType counterType) {
        this.amount = amount;
        this.counterType = counterType;
    }

    /**
     * Changes amount of counters by a given value
     * the internal amount can NOT REACH NEGATIVE values
     * @param amount given value
     * @throws RestrictedCounterAmountException if the amount would reach negative value, exception is thrown
     */
    private void changeAmount(int amount) throws RestrictedCounterAmountException {
        if(amount == 0) {
            // well, there is no reason to do anything, if you want to add nothing
            return;
        }
        if(this.amount + amount < 0) {
            throw new RestrictedCounterAmountException(
                    "Tried to change counters from " + this.amount + " to "+ (this.amount + amount) + "!");
        }
    }

    /**
     * Checks if value is negative, but does not return the result
     * It just throws an exception straight from the underground
     * @param amount value to be checked
     * @throws NegativeNotAllowedException when the value is negative, exception is thrown
     */
    private void checkNotNegative(int amount) throws NegativeNotAllowedException {
        if(amount < 0) {
            throw new NegativeNotAllowedException("Tried to add/remove " + amount + " counters.");
        }
    }

    /**
     * Increases the value of this counter by given amount
     * @param amount given amount
     * @throws NegativeNotAllowedException whenever a negative number is inputted, exception is thrown
     */
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
     * Removes an amount from this counter
     * @param amount amount to be removed
     * @throws RestrictedCounterAmountException when the result would reach negative values, exception is thrown
     * @throws NegativeNotAllowedException when the amount is negative, exception is thrown
     */
    public void removeAmount(int amount) throws RestrictedCounterAmountException, NegativeNotAllowedException {
        checkNotNegative(amount);
        changeAmount(-amount);
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Counter{" + amount + " " + counterType.toString().toLowerCase() + " counters" + '}';
    }
}
