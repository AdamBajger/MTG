package cz.mtg.game;

import cz.mtg.cards.Card;

import java.util.HashSet;
import java.util.Set;

public class Game {
    private final Stack spellStack;
    private final Set<Card> exile;
    private final Set<Card> commandZone;
    private final Set<Card> battlefield;
    private final GameFormat format;

    public Game(GameFormat format) {
        this.spellStack = new Stack();

        exile = new HashSet<>();
        commandZone = new HashSet<>();
        battlefield = new HashSet<>();
        this.format = format;
    }

    public Stack getSpellStack() {
        return spellStack;
    }

    public Set<Card> getBattlefield() {
        return battlefield;
    }

    public Set<Card> getExile() {
        return exile;
    }

    public Set<Card> getCommandZone() {
        return commandZone;
    }

    public GameFormat getFormat() {
        return format;
    }
}
