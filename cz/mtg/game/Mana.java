package cz.mtg.game;

import cz.mtg.exceptions.RestrictedManaAmountException;

import java.util.Objects;


/**
 * This class carries mana color and amount of that mana internally
 * It allows you to store mana cost in a Set and still retain different amounts of same mana type.
 * Amount of mana stored must NOT reach zero!
 */
public class Mana {
    private final Color color;
    private int amount = 1;
    private final Class<? extends Stackable> spendableOn = Stackable.class;

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
    public Mana(Color c, int amount) {
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
     * Checks if this mana can be spend on any Stackable spell (i. e. attribute spendableOn = Stackable.class)
     * If not, it checks whether the castSpell is instance of the class this mana is supposed to be spent on
     * and if it IS, it returns true, if it is NOT, it returns false.
     * @param castSpell spell being cast
     * @return True/False
     */
    public boolean canBeSpentOn(Stackable castSpell) {
        return spendableOn == Stackable.class || castSpell.getClass().isInstance(spendableOn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mana)) return false;
        Mana mana = (Mana) o;
        return getColor() == mana.getColor() &&
                Objects.equals(spendableOn, mana.spendableOn);
    }

    @Override
    public int hashCode() {

        return Objects.hash(getColor(), spendableOn);
    }

    @Override
    public String toString() {
        return "Mana{" +
                "color=" + color +
                ", amount=" + amount +
                '}';
    }
}
