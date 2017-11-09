package cz.mtg.cards.castable;

import cz.mtg.cards.AbstractCard;
import cz.mtg.cards.Card;
import cz.mtg.exceptions.InsufficientManaException;
import cz.mtg.game.*;

import java.util.HashSet;
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
    private final Set<Mana> manaCost;

    /**
     * Creates a named card with given manacost
     * @param name desired name
     */
    public AbstractCastableCard(String name, Player owner, Set<Mana> manaCost) {
        super(name, owner);
        this.manaCost = manaCost;
    }

    /**
     * This method returns the source Card causing this spell to go on stack
     *
     * @return source Card of the spell
     */
    @Override
    public Card getSource() {
        return null;
    }

    /**
     * This method returns mana cost of this castable object
     *
     * @return mana needed to cast this spell
     */
    @Override
    public Set<Mana> getManCost() {
        return manaCost;
    }

    @Override
    public Set<Color> defaultGetCardColors() {
        Set<Color> returnedColors = new HashSet<>();
        for(Mana m : getManCost()) {
            returnedColors.add(m.getColor());
        }
        return returnedColors;
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
