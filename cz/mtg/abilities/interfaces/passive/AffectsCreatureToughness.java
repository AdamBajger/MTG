package cz.mtg.abilities.interfaces.passive;


/**
 * This is an interface every ability modifying toughness must implement
 * Otherwise the ability will be omitted.
 */
public interface AffectsCreatureToughness {

    /**
     * This method is called when a creature's toughness is calculated
     * @return amount by which this ability modifies toughness
     */
    int getToughnessModifier();
}
