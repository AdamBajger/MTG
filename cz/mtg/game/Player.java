package cz.mtg.game;

import cz.mtg.cards.CardInterface;
import cz.mtg.game.targets.AttackableTarget;

import java.util.LinkedList;
import java.util.List;

/**
 * This class contains player
 *
 */
public class Player implements AttackableTarget {
    public static final int DEFAULT_LIVES_CLASSIC = 20;
    public static final int DEFAULT_LIVES_EDH = 40;

    private int lives;
    private boolean priority;

    private LinkedList<CardInterface> library;

    @Override
    public void applyDamage() {

    }

    @Override
    public void defaultTakeDamage(int damage) {

    }
}
