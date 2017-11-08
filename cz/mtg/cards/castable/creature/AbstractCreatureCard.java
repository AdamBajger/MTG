package cz.mtg.cards.castable.creature;

import cz.mtg.cards.castable.AbstractCastableCard;
import cz.mtg.game.ManaCollection;
import cz.mtg.game.Player;

/**
 * This class is here to allow PRIVATE attributes for creature
 * It will allow us to control how attributes (like power, toughness, summonningSickness, etc.) are modified
 *
 * ----------------------------
 *  TODO:
 *      define appropriate attributes HTV
 *      define appropriate getters
 *      define appropriate setters
 */
public abstract class AbstractCreatureCard extends AbstractCastableCard implements Creature {
    private boolean summoningSickness;
    private int power;
    private int toughness;

    public AbstractCreatureCard(String name, Player owner, ManaCollection manaCost, int power, int toughness) {
        super(name, owner, manaCost);
        this.summoningSickness = false;
        this.power = power;
        this.toughness = toughness;
    }

}
