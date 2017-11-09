package cz.mtg.game;

import cz.mtg.cards.Card;
import cz.mtg.game.targets.AttackableTarget;
import cz.mtg.game.zones.Library;

import java.util.LinkedList;
import java.util.Set;

/**
 * This class contains player
 * Player has name, lives and other important stuff
 *
 * Player can participate only in one game at a time. The game, player is currently participating in
 * is stored as an attribute
 */
public class Player implements AttackableTarget {
    // Game Constants for different mods
    public static final int DEFAULT_LIVES_CLASSIC = 20;
    public static final int DEFAULT_LIVES_EDH = 40;

    private String name;
    private Game gameAssigned; // this is the game the player participates in
    private int lives;
    private ManaCollection manaPool;
    private boolean priority;

    private Deck deck;

    // Game zones belonging to player
    private Library library;
    private LinkedList<Card> hand;
    private LinkedList<Card> graveyard;
    private Set<Card> sideboard;


    public ManaCollection getManaPool() {
        return manaPool;
    }

    public Game getGameAssigned() {
        return gameAssigned;
    }

    public LinkedList<Card> getHand() {
        return hand;
    }

    public LinkedList<Card> getGraveyard() {
        return graveyard;
    }

    public Set<Card> getSideboard() {
        return sideboard;
    }

    public Library getLibrary() {
        return library;
    }


    @Override
    public void defaultTakeDamage(int damage) {
        this.lives -= damage;
    }
}
