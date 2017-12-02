package cz.mtg.game.mana;

import cz.mtg.exceptions.InsufficientManaException;
import cz.mtg.game.Color;
import cz.mtg.game.ConsumesMana;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class represents a collection of mana, 
 * which can represent Player's mana collection, card's manacost etc.
 * This class controls mana spending, mana burn, mana checking and so on...
 * It stores mana objects in sets split by colors
 */
public class ManaPool {
    //private final int[] pool = new int[Color.cachedValues().length];
    // Trust me, Map really IS the right implementation -->
    private final Map<Color, ManaSet> pool = new HashMap<>();

    /**
     * Creates empty mana collection
     */
    public ManaPool() {
        for(Color c : Color.VALUES) {
            pool.put(c, new ManaSet());
        }
    }

    /**
     * Makes a copy of this mana pool to edit (pointers to Mana object are copied, not Mana objects themselves)
     * @param m mana pool to be duplicated
     * @return the copied manapool
     *
     * This method should not be used anymore as all the methods that are returning something from this class
     * are already creating copies of those things
     */
    //@Deprecated
    public static ManaPool copyOf(ManaPool m) {
        ManaPool mm = new ManaPool();
        for(Color c : m.pool.keySet()) {
            mm.pool.get(c).addAll(m.pool.get(c));
        }
        return mm;
    }

    /**
     * Just adds a mana object to the mana collection
     * @param mana Mana to be added
     */
    public boolean addMana(Mana mana) {
        return pool.get(mana.getColor()).add(mana);
    }

    /**
     * This method just takes Mana objects from given Collection and adds its values to corresponding
     * mana values in int[] pool array.
     * @param addedMana Set of mana to add
     * @see ManaPool#addMana(Mana) original addAmount(Mana)
     */
    public void addAllMana(ManaSet addedMana) {
        //System.err.println("Mana is being added.");
        for(Mana am : addedMana) {
            //System.err.println(am);
            addMana(am);
        }
    }

    /**
     * Loops over the int[] pool attribute and for each element it checks if it is greater than zero
     * foe each such element, it adds corresponding Color to the returned Set.
     * @return Set of Colors contained in this collection
     */
    public Set<Color> getColors() {
        return new HashSet<>(pool.keySet());
    }

    /**
     * Method {@link ManaSet#toSet() toSet()} is already creating a copy of the set, so we can just return the result.
     * @param c Color of which we return the mana set (we return a {@code Set<Mana>} containing mana of this color
     * @return Set of Mana objects in this mana pool of a given color
     */
    public Set<Mana> getManaOfColorSet(Color c) {
        return pool.get(c).toSet();
    }
    /**
     * Loops over grouped Mana sets (which are separated by colors) and adds those sets to the new Set
     * which is returned after that.
     * All mana objects will be unique as mana objects of the same color are handled by {@link ManaSet ManaSet} class
     * and the rest is handled based on different colors
     * @return Complete set of mana objects contained in this manapool
     */
    public Set<Mana> getAllManaSet() {
        Set<Mana> returnSet = new HashSet<>();
        for(Color color : pool.keySet()) {
            // in the pool, the mana amounts are contained aside of those Mana keys, in their own Integers
            // so if you want to obtain a Mana object correctly, you must change the mana amount in that mana object
            // to the corresponding Integer value from the Map to the constructor. Like this:
            returnSet.addAll(pool.get(color).toSet());
        }
        return returnSet;
    }

    /**
     * This method checks if a given player has enough mana to cast this
     * The casting player is always the owner of the casted card or owner of the casting source
     * In every Mana check also a conditions must be checked
     * @return True if there is enough mana. False otherwise
     */
    public boolean notEnoughMana(ConsumesMana spell) {
        ManaSet manaCost = spell.getManaCost();

        boolean manaFound;
        // for each mana color in manaCost, check if there is enough mana of that color in checkedManaPool
        for(Mana manaInCost : manaCost) {
            // this tries to spend the mana on the copied mana mana pool
            manaFound = false;
            for(Mana m : pool.get(manaInCost.getColor())) {
                if(m.equals(manaInCost) && m.getAmount() >= manaInCost.getAmount()) {
                    manaFound = true;
                    break;
                }
                // If no satisfying mana was found, return false

            }
            if(!manaFound) {
                return false;
            }

        }

        // no insufficient mana found ---> return false
        // also means that the mana cost of the spell was zero, so no iteration happened and... it's just okay
        return false;
    }

    /**
     * Tries to spend a mana defined by a given mana object
     * Even there we need to test the Mana object conditions
     * This method loops over mana objects contained in the mana pool and uses the pointers to CONTAINED MANA
     * to remove it (i. e. spending it), so the mana from
     *
     * If the mana can be spent, it IS SPENT, while when it is not, exception is thrown
     * @param mana given mana object
     * @param spell spell on which we spend the mana
     * @return True if mana was spend completely (zero mana of that type left), false if there is some amount left
     * @throws InsufficientManaException if the mana was not there or there was not enough mana
     * @see ManaSet#remove(Mana) remove() method
     */
    public boolean spendMana(Mana mana, ConsumesMana spell) throws InsufficientManaException {
        // gets mana set of the same color as given mana has
        for(Mana m : pool.get(mana.getColor())) {
            System.err.println("Mana checked:\n" + m.toString() + "\n Mana inputted to the function:\n" + mana.toString());
            // for every mana there, it tries if the mana can be spent on the spell and spell can be cast by this mana
            if(m.equals(mana) && m.canBeSpentOn(spell) && spell.manaCanBeSpendOnThis(mana)) {
                return pool.get(m.getColor()).remove(mana);
            }
        }
        throw new InsufficientManaException("Spent mana not found.", mana);
    }

    /**
     * This method is used to spend specific amount of mana from this manapool, described by a Set of mana objects
     *
     *
     * @param cost Cost to be spent
     *
     */
    public void spendMana(ManaSet cost, ConsumesMana spell) throws InsufficientManaException {
        for(Mana m : cost) {
            spendMana(m, spell);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ManaPool{");
        for(Color c : pool.keySet()) {
            if(pool.get(c).iterator().hasNext()) {
                sb.append("{").append(pool.get(c).toSimpleString()).append("}");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
