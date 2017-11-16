package cz.mtg.abilities.interfaces.passive;

/**
 * This is an interface every ability modifying damage must implement
 * Otherwise the ability will be omitted.
 */
public interface AffectsCreaturePower {
    /**
     * This method is called when a creature's damage amount is counted
     * @return amount by which this ability modifies damage
     */
    int getPowerModifier();
}
