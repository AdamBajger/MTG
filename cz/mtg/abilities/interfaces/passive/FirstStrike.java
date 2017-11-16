package cz.mtg.abilities.interfaces.passive;

import cz.mtg.abilities.AbilityPassive;
import cz.mtg.cards.Card;

/**
 * This is mostly a markup ability :)
 */
public class FirstStrike extends AbilityPassive {
    public FirstStrike(Card source) {
        super("First strike", "This creature deals combat damage before creatures without first strike.", source);
    }
}
