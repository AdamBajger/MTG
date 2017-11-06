package cz.mtg.game;

import cz.mtg.exceptions.RestrictedManaAmountException;

/**
 * This class carries mana color and amount of that mana internally
 * It allows you to store mana cost in a Set and still retain different amounts of same mana type.
 * Amount of mana stored must NOT reach zero!
 */
public class Mana {
    private Color color;
    private int amount = 1;

    /**
     * Constructs a mana unit with desired color and amount 1
     * @param c desired color
     */
    public Mana(Color c) {
        this.color = c;
    }

    /**
     * Constructs a mana unit with desired color and desired amount
     * @param c desired color
     * @param amount desired amount
     */
    public Mana(Color c, int amount) throws RestrictedManaAmountException {
        this(c);
        if(amount > 0) {
            this.amount = amount;
        } else {
            throw new RestrictedManaAmountException("Amount of mana must NOT be initialized as zero or less!");
        }

    }

    /**
     * Adds some amount to this mana unit
     * Adding a negative value will subtract mana amount
     * @param amount amount of mana to be added
     */
    public void addMana(int amount) throws RestrictedManaAmountException {
        if(this.amount + amount <= 0) {
            throw new RestrictedManaAmountException("Amount of mana must NOT reach zero or below!");
        } else {
            this.amount += amount;
        }
    }

    public Color getColor() {
        return color;
    }

    public int getAmount() {
        return amount;
    }

    /**
     * There is explicitly said that two mana objects are equal, if theyr color is equal
     * this prevent having more Mana objects in one Set
     * @param obj compared object
     * @return True if input obj is equal to this, False otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == this.getClass() && ((Mana)obj).color == this.color;
    }
}
