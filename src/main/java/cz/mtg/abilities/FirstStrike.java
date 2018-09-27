package cz.mtg.abilities;

import cz.mtg.abilities.abstracts.passive.AbilityPassive;
import cz.mtg.cards.Card;

/**
 * This is mostly a markup ability :)
 */
public class FirstStrike extends AbilityPassive {
    public FirstStrike(Card source) {
        super("first strike", "This creature deals combat damage before creatures without first strike or double strike.", source);
    }
}
