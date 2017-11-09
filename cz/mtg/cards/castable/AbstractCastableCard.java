package cz.mtg.cards.castable;

import cz.mtg.cards.AbstractCard;
import cz.mtg.exceptions.InsufficientManaException;
import cz.mtg.game.CardPlacement;
import cz.mtg.game.Color;
import cz.mtg.game.ManaCollection;
import cz.mtg.game.Player;

import java.util.Set;

/**
 * This class contains methods that a castable card has to have
 * most important is the method Cast(), described ind javadoc by the method.
 * ------------------------
 *  TODO
 *      consider making more constructors for Color[] and List of colors.
 *      decide how to implement mana cost, which data structure to use!!!
 */
public abstract class AbstractCastableCard extends AbstractCard implements CastableCard {
    private final ManaCollection manaCost;

    /**
     * Creates a named card with given manacost
     * @param name desired name
     */
    public AbstractCastableCard(String name, Player owner, ManaCollection manaCost) {
        super(name, owner);
        this.manaCost = manaCost;
    }

    @Override
    public Set<Color> defaultGetCardColors() {
        return manaCost.getColors();
    }

    /**
     * Appends info about mana cost to a given string builder
     * @param sb given string builder
     */
    private void appendManaCostInfo(StringBuilder sb) {
        sb.append(", ");
        sb.append(manaCost);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendBasicInfo(sb);
        appendManaCostInfo(sb);
        appendStateInfo(sb);

        return sb.toString();
    }


}
