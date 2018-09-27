package cz.mtg.game;

public enum GameFormat {
    CLASSIC, EDH, TEST;

    private int MIN_CARDS;
    private int INIT_LIVES;

    static {
        TEST.MIN_CARDS = 0;
        TEST.INIT_LIVES = 42;
        CLASSIC.MIN_CARDS = 60;
        CLASSIC.INIT_LIVES = 20;
        EDH.MIN_CARDS = 101;
        EDH.INIT_LIVES = 40;
    }

    public int getMinCards() {
        return MIN_CARDS;
    }

    public int getInitLives() {
        return INIT_LIVES;
    }
}
