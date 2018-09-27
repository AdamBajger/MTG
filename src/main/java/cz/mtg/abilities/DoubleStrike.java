package cz.mtg.abilities;

import cz.mtg.abilities.abstracts.passive.AbilityPassive;
import cz.mtg.cards.Card;

/**
 * This is mostly a markup ability :)
 */
public class DoubleStrike extends AbilityPassive {
    public DoubleStrike(Card source) {
        super("double strike", "This creature deals combat damage twice. " +
                "Once before creatures without first strike and the second as usual.", source);
    }
}
