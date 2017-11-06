package cz.mtg.cards.castable.creature;

import cz.mtg.cards.CardInterface;
import cz.mtg.cards.castable.CastableCardInterface;
import cz.mtg.exceptions.AlreadyTappedOrUntappedException;
import cz.mtg.exceptions.NegativeNotAllowedException;
import cz.mtg.game.Player;
import cz.mtg.game.targets.DamageableTarget;

/**
 * Interface for some basic creature functionality
 * -------------------------
 *  TODO:
 *      Complete attack() method
 *      ---> develop attack mechanics
 */
public interface Creature extends CastableCardInterface, DamageableTarget {
    /**
     * Casual check if the creature has summoning sickness
     * do not use this (unless you know what you are doing) in actual checking.
     * It is here only to allow interface overwriting
     * Use {@link Creature#hasSummoningSickness()}
     * @return True/False
     */
    boolean defaultHasSummoningSickness();

    /**
     * Overridable method
     * Checks if creature has summoning sickness
     * this is here to allow Haste ability to be implementable
     * @return True/False
     */
    default boolean hasSummoningSickness() {
        return defaultHasSummoningSickness();
    };

    /**
     * Complex getter to get actual power of a creature
     * calculates with default power and bonus power
     * @return Actual power of the creature
     */
    int getPower();

    /**
     * Complex getter to get actual toughness of a creature
     * calculates with default toughness and bonus toughness
     * @return Toughness of a creature
     */
    int getToughness();

    /**
     * Trie to add creature to attacking creature group
     * not developed yet...
     *
     * method incomplete
     * @param whichPlayer which player is attacked
     * @return True if creature was successfully assigned to attack, False otherwise
     * -----------------------------------------
     *  TODO:
     *      Keep in mind that this DOES NOT manage damage itself
     *      ---> leave that for damage methods {@link Creature#defaultDealDamage(DamageableTarget) one} and {@link Creature#dealDamage(DamageableTarget) two}
     */
    default boolean attack(Player whichPlayer) {
        if(this.hasSummoningSickness()) {
            return false;
        }
        try {
            this.tap();
        } catch(AlreadyTappedOrUntappedException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * Default method to deal damage
     * This method actually deals damage to a TARGET from this
     * @param damageableTarget Target to be damaged
     */
    void defaultDealDamage(DamageableTarget damageableTarget) throws NegativeNotAllowedException;




    /**
     * Overridable method for dealing the damage to a target
     * @param damageableTarget target to be dealt damage
     */
    default void dealDamage(DamageableTarget damageableTarget) throws NegativeNotAllowedException {
        defaultDealDamage(damageableTarget);
    }

}
