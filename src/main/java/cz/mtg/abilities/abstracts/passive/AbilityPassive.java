package cz.mtg.abilities.abstracts.passive;

import cz.mtg.abilities.abstracts.Ability;
import cz.mtg.cards.Card;

/**
 * This class represents a passive ability, that is triggered automatically
 * these abilities need interfaces to be functional. Particular interfaces are recognized by game procedures,
 * and are being used through them.
 * Each interface has its own methods that are required in particular procedures of the game.
 * --> those execute the ability's effects.
 * TODO: complete implementation
 */
public abstract class AbilityPassive extends Ability {

    public AbilityPassive(String keyWord, String effectDescription, Card source) {
        super(keyWord, effectDescription, source);
    }

    public AbilityPassive(String effectDescription, Card source) {
        super(effectDescription, source);
    }
}
