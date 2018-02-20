package cz.mtg.cards.castable.creature;

import cz.mtg.abilities.abstracts.Ability;
import cz.mtg.abilities.abstracts.passive.AffectsBlockingOf;
import cz.mtg.cards.castable.CastableCard;
import cz.mtg.exceptions.AlreadyTappedOrUntappedException;
import cz.mtg.exceptions.InvalidActionException;
import cz.mtg.exceptions.InvalidTargetException;
import cz.mtg.exceptions.ProtectionFromSourceException;
import cz.mtg.game.Player;
import cz.mtg.game.targets.DamageableTarget;

/**
 * Interface for some basic creature functionality
 * -------------------------
 *  TODO:
 *      Complete attack() method
 *      ---> develop attack mechanics
 */
public interface Creature extends CastableCard, DamageableTarget {




    /**
     * Getter for getting actual power of a creature
     * with all modifiers applied
     * Actual power can not fall below 0
     * @return actual power of a creature
     */
    int getActualPower();

    /**
     * This will change the power modifier for the creature
     * input number is added to the modifier attribute
     * @param amount a whole number
     */
    void modifyPower(int amount);

    /**
     * Counts actual toughness of a creature, with all bonuses applied
     * Actual toughness can not fall below 0
     * @return actual toughness of a creature
     */
    int getActualToughness();

    /**
     * This will change the toughness modifier for the creature
     * input number is added to the modifier attribute
     * @param amount a whole number
     */
    void modifyToughness(int amount);

    /**
     * Checks if creature should die.
     * Checks all circumstances under which creature should die and if any of those circumstances happened,
     * True is returned. If the creature is okay, false is returned.
     * @return True if creature should die, False otherwise
     */
    default boolean creatureShouldDie(){
        return getDamageTaken() >= getActualToughness();
    }

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
    }



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
     *      ---> leave that for damage methods {@link Creature#dealDamage(DamageableTarget) one} and {@link Creature#dealDamage(DamageableTarget) two}
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
        // TODO: assign creature for attack
        // TODO: fire event --> creature attacks
        return true;

    }

    /**
     * This method is used to declare this creature as blocking
     * TODO: check for block validity --> canBeTargetOfSource()
     * TODO: complete class
     * @param creature creature to be blocked
     */
    default void block(Creature creature) throws InvalidTargetException {
        if(!creature.canBeTargetOfSource(this)) {
            throw new InvalidTargetException("This creature cannot be blocked with this creature!", creature, this);
        }
        for (Ability a : creature.getAbilities()) {
            if (a instanceof AffectsBlockingOf) {
                if (!((AffectsBlockingOf) a).canBeBlockedBy(this)) {
                    throw new InvalidTargetException("This creature cannot be blocked with this creature!", creature, this);
                }
            }
        }
        //todo: assign for blocking
    }

    /**
     * Default method to deal damage
     * This method actually deals damage to a TARGET from this creature
     * For example when Creature attacks and gets blocked, each of these creatures use this method
     * to deal damage to each other
     *
     */
    default void dealDamage(DamageableTarget target) throws ProtectionFromSourceException {
        target.takeDamage(getActualPower(), this);
    }

    /**
     * Returns damage taken this turn by this creature
     * damage taken can not reach negative values
     * @return damage taken
     */
    int getDamageTaken();

}
