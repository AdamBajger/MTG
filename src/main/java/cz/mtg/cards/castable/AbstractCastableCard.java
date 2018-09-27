package cz.mtg.cards.castable;

import cz.mtg.cards.AbstractCard;
import cz.mtg.cards.Card;
import cz.mtg.game.*;
import cz.mtg.game.mana.Mana;
import cz.mtg.game.mana.ManaSet;

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
    private final ManaSet manaCost;

    /**
     * Creates a named card with given manacost
     * @param name desired name
     */
    public AbstractCastableCard(String name, Player owner, ManaSet manaCost) {
        super(name, owner);
        this.manaCost = manaCost;
    }

    /**
     * This method returns mana cost of this castable object
     * @return mana needed to cast this spell
     */
    @Override
    public ManaSet getManaCost() {
        return manaCost;
    }

    @Override
    public Set<Color> defaultGetCardColors() {
        Set<Color> returnedColors = new HashSet<>();
        for(Mana m : getManaCost()) {
            returnedColors.add(m.getColor());
        }
        return returnedColors;
    }

    /**
     * Appends info about mana cost to a given string builder
     * @param sb given string builder
     */
    protected void appendManaCostInfo(StringBuilder sb) {
        sb.append(manaCost);
        sb.append(", ");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendBasicInfo(sb);
        appendManaCostInfo(sb);

        appendStateInfo(sb);
        sb.append('}');
        return sb.toString();
    }


}
