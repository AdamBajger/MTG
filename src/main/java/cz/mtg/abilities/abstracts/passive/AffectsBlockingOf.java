package cz.mtg.abilities.abstracts.passive;

import cz.mtg.cards.castable.creature.Creature;

/**
 * This ability affects blocking of this creature
 * For example abilities like "This creature cant't be blocked by..." or unblockable, intimidate, swampwalk, etc...
 *
 */
public interface AffectsBlockingOf {
    boolean canBeBlockedBy(Creature creature);
}
