package cz.mtg.game;

/**
 * Implementation of mana colors
 */
public enum Color {
    WHITE, BLUE, BLACK, RED, GREEN, COLORLESS;

    public static final Color[] VALUES = values();

    /**
     * This method returs cached array of Colors to prevent repeating creating of new arrays
     * @return All Color[] array
     */
    public static Color[] cachedValues() {
        return VALUES;
    }


}
