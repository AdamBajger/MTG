package cz.mtg.game;

import cz.mtg.cards.Card;
import cz.mtg.game.targets.AttackableTarget;
import cz.mtg.game.zones.Library;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * This class contains player
 *
 */
public class Player implements AttackableTarget {
    // Game Constants for different mods
    public static final int DEFAULT_LIVES_CLASSIC = 20;
    public static final int DEFAULT_LIVES_EDH = 40;

    private String name;
    private int lives;
    private ManaCollection manaPool;
    private boolean priority;

    private Library library;
    private LinkedList<Card> hand;

    public Library getLibrary() {
        return library;
    }


    @Override
    public void defaultTakeDamage(int damage) {
        this.lives -= damage;
    }
}
