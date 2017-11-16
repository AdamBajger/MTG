package cz.mtg.abilities;

import cz.mtg.cards.Card;

/**
 * This class represents a passive ability, that is triggered automatically
 * TODO: write JavaDoc
 * TODO: complete implementation
 */
public abstract class AbilityPassive extends Ability {

    public AbilityPassive(String name, String effectDescription, Card source) {
        super(name, effectDescription, source);
    }

    public AbilityPassive(String effectDescription, Card source) {
        super(effectDescription, source);
    }
}
