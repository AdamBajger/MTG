package cz.mtg.game;

import cz.mtg.cards.Card;
import cz.mtg.exceptions.InsufficientManaException;

/**
 * This class contains one ability, that can be activated. Its effects, conditions and everything
 * I forgot what the implementation should be about, so ...
 * i will come back to this later, I have to time now
 */
public class Ability implements Stackable {
    private String name;
    private String effectDescription;
    private ManaCollection cost;
    private Card source; // this is the card to which this ability is sticked to


}
