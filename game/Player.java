package cz.mtg.game;

import cz.mtg.game.targets.AttackableTarget;

/**
 * This class contains player
 *
 */
public class Player implements AttackableTarget {
    public static final int DEFAULT_LIVES_CLASSIC = 20;
    public static final int DEFAULT_LIVES_EDH = 40;

    private int lives;
    private boolean priority;

    @Override
    public void applyDamage() {

    }

    @Override
    public void defaultTakeDamage(int damage) {

    }
}
