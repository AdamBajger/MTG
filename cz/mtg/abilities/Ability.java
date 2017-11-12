package cz.mtg.abilities;

import cz.mtg.cards.Card;

public abstract class Ability {
    private String name;
    private String effectDescription;
    private Card source; // this is the card to which this ability is sticked to

    public Card getSource() {
        return source;
    }
}
