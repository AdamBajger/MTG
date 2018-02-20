package cz.mtg.game.mana;

import cz.mtg.cards.Card;
import cz.mtg.exceptions.RestrictedManaAmountException;
import cz.mtg.game.Color;
import cz.mtg.game.ConsumesMana;

import java.util.Objects;


/**
 * This class carries mana color and amount of that mana internally
 * It allows you to store mana cost in a Set and still retain different amounts of same mana type.
 * Amount of mana stored must NOT reach zero!
 * Mana can be generated for a specific purpose, so you have to have a mechanism how to check
 * if mana is spent on legal target.
 * -->  this is done through the fact that this clas is abstract and its subclasses need to contain
 *      a method which checks these conditions
 * Also there is a need of being able to differentiate two different mana objects.
 * -->  that is done by a method {@code getUnifyingHash()}. this method is NOT a regular hash function
 *      and is there particularly to allow an {@code equals()} method
 *      this method gives some unifying hash, that represents only the conditions, under which a mana can be spend
 */
public abstract class Mana implements Cloneable{
    private final Color color;
    private int amount = 1;
    private final Card source; // may be useful in future card implementations
    private String spendConditionDescription;

    /**
     * Constructs a mana unit with desired color and desired amount
     * The conditions needed to allow spending this mana are none, so spendConditionDescription attribute
     * is set to empty String
     * @param c desired color
     * @param amount desired amount
     * @param source the source card generating this mana
     */
    public Mana(Color c, int amount, Card source) {
        this.color = c;
        this.source = source;
        this.spendConditionDescription = "";
        if(amount > 0) {
            this.amount = amount;
        } else {
            throw new RestrictedManaAmountException("Amount of mana must NOT be initialized as zero or less!");
        }

    }

    /**
     * Constructs a mana unit with desired color and desired amount
     * Conditions needed to spend this mana are described.
     * @param c desired color
     * @param amount desired amount
     * @param source the source card generating this mana
     * @param spendConditionDescription description of what condition must be met in order to allow spending this mana
     */
    public Mana(Color c, int amount, Card source, String spendConditionDescription) {
        this(c, amount, source);
        this.spendConditionDescription = spendConditionDescription;
    }


    /**
     * Adds some amount to this mana unit
     * Adding a negative value will subtract mana amount
     * @param amount amount of mana to be added
     */
    public void addAmount(int amount) throws RestrictedManaAmountException {
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

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Card getSource() {
        return source;
    }

    /**
     * Checks if this mana can be spend on any Castable spell (i. e. attribute spendableOn = Castable.class)
     * If not, it checks whether the castSpell is instance of the class this mana is supposed to be spent on
     * and if it IS, it returns true, if it is NOT, it returns false.
     * @param castSpell spell being cast
     * @return True/False
     */
    public abstract boolean canBeSpentOn(ConsumesMana castSpell);

    /**
     * This will give you some hash, which should be unique
     * The uniqueness is there for mana objects with different spendable conditions
     * The hash is generated from a string that describes the spend conditions
     * @return a hash
     */
    private int getUnifyingHash() {
        return spendConditionDescription.hashCode();
    }

    /**
     * Two mana objects are equal, if their color is equal and the conditions for spending them are the same.
     * @param o compared object
     * @return as usual
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mana)) return false;
        Mana mana = (Mana) o;
        return getColor() == mana.getColor() &&
                getUnifyingHash() == mana.getUnifyingHash();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getUnifyingHash());
    }

    @Override
    public String toString() {
        return "Mana{" +
                "color=" + color +
                ", amount=" + amount +
                ", source=" + source.getName() +
                ", conditions=" + spendConditionDescription +
                '}';
    }
    @Override
    public Object clone() throws CloneNotSupportedException {

        return super.clone();
    }
}
