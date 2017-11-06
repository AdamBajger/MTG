package cz.mtg.game;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a collection of mana, 
 * which can represent Player's mana collection, card's manacost etc.
 * This class controls mana spending, mana burn, mana checking and so on...
 *
 * ------------------------------------
 *  TODO
 *      Concept of this class is really only a prototype and should be discussed
 *      and eventually REWORKED
 *      Consider using HASHMAP!!!!
 */
public class ManaCollection {
    private final int[] pool = new int[Color.cachedValues().length];

    /**
     * Constructs a mana collection based on Mana[] array
     * @param pool array of Mana objects
     */
    public ManaCollection(Mana[] pool) {
        for(Mana m : pool) {
            this.pool[m.getColor().ordinal()] += m.getAmount();
        }
    }

    /**
     * Creates mana collection from array of Colors
     * This constructor uses array of colors, because creating a Mana[] array throws exceptions
     *
     * @param colors Array of Colors
     */
    public ManaCollection(Color[] colors) {
        for(Color c : colors) {
            this.pool[c.ordinal()] += 1;
        }
    }

    /**
     * Gets you mana of a color
     * attribute pool is generated based on the Color enum structure thus modifying Color enum elements
     * does not affect functionality of this Class or method
     * @param c Color of mana, its amount is returned
     * @return amount of mana
     */
    public int getManaOfColorAmount(Color c) {
        return pool[c.ordinal()];
    }

    /**
     * Adds an amount of mana of given color to this mana collection
     * @param c given color
     * @param amount amount of given mana to be added
     */
    public void addManaOfColor(Color c, int amount) {
        pool[c.ordinal()] += amount;
    }

    /**
     * Adds one mana of a given color to this mana collection
     * @param c given color
     */
    public void addManaOfColor(Color c) {
        addManaOfColor(c, 1);
    }

    /**
     * Just adds one mana to the int[] pool array
     * @param mana Mana to be added
     */
    public void addMana(Mana mana) {
        pool[mana.getColor().ordinal()] += mana.getAmount();
    }

    /**
     * This method just takes Mana objects from given Collection and adds its values to corresponding
     * mana values in int[] pool array.
     * @param addedMana Set of mana to add
     * @see ManaCollection#addMana(Mana) original addMana(Mana)
     */
    public void addMana(Collection<Mana> addedMana) {
        for(Mana am : addedMana) {
            addMana(am);
        }
    }

    /**
     * Loops over the int[] pool attribute and for each element it checks if it is greater than zero
     * foe each such element, it adds corresponding Color to the returned Set.
     * @return Set of Colors contained in this collection
     */
    public Set<Color> getColors() {
        Set<Color> returnSet = new HashSet<>();
        for(int i = 0; i < Color.cachedValues().length; i++) {
            if(pool[i] > 0) {
                // if the value of the mana is greater than 0, it is added as color to the Set
                returnSet.add(Color.cachedValues()[i]);
            }
        }
        return returnSet;
    }




}
