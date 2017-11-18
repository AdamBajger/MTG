package cz.mtg.game;

import cz.mtg.cards.Card;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

/**
 * This class represents a collection of mana, 
 * which can represent Player's mana collection, card's manacost etc.
 * This class controls mana spending, mana burn, mana checking and so on...
 *
 * This class also provides (or will provide) static methods for converting mana arrays to sets or so...
 * ------------------------------------
 *  TODO
 *      Rework this to work and HashSet, because we will need more Mana types, that just those basic
 *      For example mana, that can be spent only to certain spells
 *      So we will never know which types of mana will be added
 */
public class ManaCollection {
    //private final int[] pool = new int[Color.cachedValues().length];
    // Trust me, Map really IS the right implementation -->
    private final Map<Mana, Integer> pool = new HashMap<>();

    /**
     * Creates empty mana collection
     */
    public ManaCollection() {}


    /**
     * Constructs a mana collection based on give Set of Mana objects
     * @param pool given Set of Mana objects
     */
    public ManaCollection(Set<Mana> pool) {
        for(Mana m : pool) {
            this.pool.put(m, m.getAmount());
        }
    }

    public static ManaCollection copyOf(ManaCollection m) {
        ManaCollection mm = new ManaCollection();
        for(Mana mana : m.pool.keySet()) {
            mm.addMana(mana);
        }
        return mm;
    }

    /**
     * Gets you mana of a color
     * attribute pool is generated based on the Color enum structure thus modifying Color enum elements
     * does not affect functionality of this Class or method
     * @param c Color of mana, its amount is returned
     * @return amount of mana
     */
    public int getManaOfColorAmount(Color c) {
        // check if that mana is even there
        Integer checkedMana = pool.get(new Mana(c, 1));
        if(checkedMana == null) {
            return 0;
        } else {
            return checkedMana;
        }
    }

    /**
     * Adds an amount of mana of given color to this mana collection
     * @param c given color
     * @param amount amount of given mana to be added
     */
    public void addManaOfColor(Color c, int amount) {
        Mana key = new Mana(c, 1); // create Mana object from color as a key
        Integer currentAmount = pool.get(key); // if the key was there, it will get a current value, else it gets null
        // increment current value or add a whole new key with value
        pool.put(key, currentAmount == null ? amount : amount + currentAmount);
    }

    /**
     * Adds one mana of a given color to this mana collection
     * @param c given color
     */
    public void addManaOfColor(Color c) {
        addManaOfColor(c, 1);
    }

    /**
     * Just adds one mana to the mana collection
     * @param mana Mana to be added
     */
    public void addMana(Mana mana) {
        Integer currentAmount = pool.get(mana); // if the key was there, it will get a current value, else it gets null
        // increment current value or add a whole new key with value
        pool.put(mana, currentAmount == null ? mana.getAmount() : mana.getAmount() + currentAmount);
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
        for(Mana key : pool.keySet()) {
            returnSet.add(key.getColor());
        }
        return returnSet;
    }

    public Set<Mana> getManaSet() {
        Set<Mana> returnSet = new HashSet<>();
        for(Mana key : pool.keySet()) {
            // in the pool, the mana amounts are contained aside of those Mana keys, in their own Integers
            // so if you want to obtain a Mana object correctly, you must create new Mana object and pass
            // the corresponding Integer value from the Map to the constructor. Like this:
            returnSet.add(new Mana(key.getColor(), pool.get(key)));
        }
        return returnSet;
    }

    public Map<Mana, Integer> getPool() {
        return pool;
    }

    /**
     * This method is used to spend specific amount of mana from this manapool
     *
     * @param cost
     */
    public void spendMana(Set<Mana> cost) {

    }

    @Override
    public String toString() {
        return "ManaCollection{" +
                "pool=" + pool.toString() +
                '}';
    }
}
