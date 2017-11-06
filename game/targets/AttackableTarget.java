package cz.mtg.game.targets;

/**
 * This interface is implemented by every object that can be a valid attack target
 */
public interface AttackableTarget extends DamageableTarget {
    /**
     * Target takes damage
     * For player that means life subtraction
     * For Planeswalker that means loyalty counters removal
     */
    void applyDamage();
}
