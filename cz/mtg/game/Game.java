package cz.mtg.game;

import cz.mtg.cards.Card;

import java.util.Set;

public class Game {
    private Stack spellStack;
    private Set<Card> exile;
    private Set<Card> commandZone;
    private Set<Card> battlefield;

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
}
