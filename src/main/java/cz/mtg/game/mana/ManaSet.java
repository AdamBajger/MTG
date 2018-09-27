package cz.mtg.game.mana;

import cz.mtg.exceptions.InsufficientManaException;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * This class is in fact a Set implementation, but I am not experienced enough to jump into extending a Collection.
 * because of that I decided to just make my own class
 * This class works as a Mana container. It cares about adding manas to itself in the right way
 * For example it makes sure that there will only be one Mana object of color Red wint no special conditions
 * and when adding another one basic red manas, it will just add its amount to the current Mana object
 * and when you add some special red manas, for example red manas, that you can spend only on creatures
 * it will add it as a brand new Mana object
 */
public class ManaSet implements Iterable<Mana> {
    private Set<Mana> manas = new HashSet<>();

    /**
     * Default constructor
     */
    public ManaSet(){};

    /**
     * Constructs a new instance from existing {@code Set<Mana>}
     * @param pls set of mana objects
     */
    public ManaSet(Collection<Mana> pls) {
        manas.addAll(pls);
    }

    /**
     * Adds a Mana to this set
     * Checks if another manas of the same type is already there and if it is so, it combines them.
     * @param m manas to be added
     * @return True on success, false otherwise? im not sure :D
     */
    public boolean add(Mana m) {
        for(Mana mana : manas) {
            if(mana.equals(m)) {
                mana.addAmount(m.getAmount());
                return true;
            }
        }
        try {
            return manas.add((Mana)m.clone());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Mana could not be cloned.", e);
        }
    }

    public boolean addAll(ManaSet ms) {
        for(Mana m : ms) {
            if(!manas.add(m)) return false;
        }
        return true;
    }

    public boolean contains(Mana m) {
        return manas.contains(m);
    }

    /**
     * Method to remove a Mana from this set
     * It removes only an amount defined by given Mana object from equal Mana object in this Set
     * @param m given Mana object
     * @return true if mana was completely removed, fasle if some amount of that mana is left
     * @throws InsufficientManaException when there is no mana to be removed
     */
    public boolean remove(Mana m) throws InsufficientManaException {
        for(Mana mana : manas) {
            if(mana.equals(m)) {
                int currentAmount = mana.getAmount();
                int manaAmountToBeRemoved = m.getAmount();
                System.err.println("Current mana: " + currentAmount + " to be removed: " + manaAmountToBeRemoved);
                if(currentAmount < manaAmountToBeRemoved) {
                    throw new InsufficientManaException("Not enough mana to be removed", m);
                } else if(currentAmount == manaAmountToBeRemoved) {
                    // if we have to remove exactly same mana amount as the amount stored, just remove the object itself
                    if(!manas.remove(mana)) {
                        throw new RuntimeException("Mana could not be removed from ManaSet" +
                                " even though there was an equivalent Mana object!");
                    } else {
                        // true, as the mana was removed completely
                        return true;
                    }
                } else {
                    // if there is more mana than we need to spend
                    mana.addAmount(-manaAmountToBeRemoved);
                    //false, as the mana was NOT removed completely
                    return false;
                }
            }
        }
        throw new InsufficientManaException("No mana of that type present!", m);

    }

    /**
     * Returns an iterator over elements of type {@code Mana}.
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator<Mana> iterator() {
        return manas.iterator();
    }

    /**
     * returns a copy of internal Mana collection
     * does NOT allow you to modifie the internal collection
     * that can be done only via methods of THIS CLASS
     * @return copy of internal Mana collection
     */
    public Set<Mana> toSet() {
        return new HashSet<>(manas);
    }


    public String toSimpleString() {
        StringBuilder sb = new StringBuilder();
        for(Mana m : manas) {
            sb.append(m.getColor().toString()).append(": ").append(m.getAmount());
        }
        return sb.toString();
    }
}
