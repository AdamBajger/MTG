package cz.mtg.abilities.abstracts.passive;

import cz.mtg.game.Source;

/**
 * This is an ability that affects the conditions under which the holding card/player can be targeted
 * For example:
 *  Protections from colors
 *  Protections from creatures
 *  Hexproof
 *  Shroud
 */
public interface AffectsTargeting {
    /**
     * This method checks whether a card or player can be targeted
     * by a source
     * @param source source targeting the holder of this ability
     * @return true if target is valid, false if not
     */
    boolean canBeTargetOfSource(Source source);
}
