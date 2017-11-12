package cz.mtg.abilities;

import cz.mtg.cards.Card;
import cz.mtg.exceptions.InsufficientManaException;
import cz.mtg.game.ManaCollection;
import cz.mtg.game.Stackable;

/**
 * This class contains one ability, that can be activated. Its effects, conditions and everything
 * I forgot what the implementation should be about, so ...
 * i will come back to this later, I have to time now
 */
public abstract class AbilityStackable extends Ability implements Stackable {

    private ManaCollection cost;


}
