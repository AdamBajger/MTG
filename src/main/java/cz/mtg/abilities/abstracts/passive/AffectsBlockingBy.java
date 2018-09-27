package cz.mtg.abilities.abstracts.passive;

import cz.mtg.cards.castable.creature.Creature;

/**
 * This interface is implemented by abilities that affect how a crerature block or modify the target set,
 * that a creature can block.
 * This interface is only for abilities that restrict targets.
 * Abilities like reach, which are connected with single abilities (here flying) shall be included in the definition of
 * those connected abilities (meant flying)
 *  For example:
 *      Reach
 *      "Whenever this creature blocks..." abilities
 *      Flying
 */
public interface AffectsBlockingBy {
    /**
     * This method tells you if the creature inputted can be blocked by the creature carrying this ability.
     * @param creature creature we want to know if it can be blocked
     * @return whether the creature inputted can be blocked by creature carrying this ability
     */
    boolean canBlock(Creature creature);
}
