package cz.mtg.cards.castable.creature;

import cz.mtg.cards.castable.CastableCard;
import cz.mtg.game.Mana;

import java.util.Set;

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
public abstract class CreatureCard extends CastableCard implements Creature {
    private boolean summoningSickness;
    private int power;
    private int toughness;

    public CreatureCard(String name, Set<Mana> manaCost, int power, int toughness) {
        super(name, manaCost);
        this.summoningSickness = false;
        this.power = power;
        this.toughness = toughness;
    }

}
