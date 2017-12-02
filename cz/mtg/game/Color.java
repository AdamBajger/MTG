package cz.mtg.game;

/**
 * Implementation of mana colors
 */
public enum Color {
    WHITE, BLUE, BLACK, RED, GREEN, COLORLESS;

    private String letter;
    public static final Color[] VALUES = values();

    static {
        WHITE.letter = "W";
        BLUE.letter = "U";
        BLACK.letter = "B";
        RED.letter = "R";
        GREEN.letter = "G";
        COLORLESS.letter = "C";
    }

    @Override
    public String toString() {
        return letter;
    }
}
