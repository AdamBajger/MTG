package cz.mtg.cards.castable;

import cz.mtg.cards.AbstractCard;
import cz.mtg.game.CardPlacement;
import cz.mtg.game.Color;
import cz.mtg.game.ManaCollection;

import java.util.Set;

/**
 * This class contains methods that a castable card has to have
 * most important is the method Cast(), described ind javadoc by the method.
 * ------------------------
 *  TODO
 *      consider making more constructors for Color[] and List of colors.
 *      decide how to implement mana cost, which data structure to use!!!
 */
public abstract class CastableCard extends AbstractCard implements CastableCardInterface {
    private final ManaCollection manaCost;

    /**
     * Creates a named card with given manacost
     * @param name desired name
     */
    public CastableCard(String name, ManaCollection manaCost) {
        super(name);
        this.manaCost = manaCost;
    }

    @Override
    public Set<Color> defaultGetCardColors() {
        return manaCost.getColors();
    }

    /**
     * For now this method is abstract, but maybe it is not necessary, let's decide later
     * It takes THIS card and creates a spell carrying this card.
     * That spell is then moved to STACK, where it stays according to MTG rules
     * @return Spell containing cast card
     */
    public void Cast() {
        super.setCardPlacement(CardPlacement.STACK);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CastableCard{" + super.getName() + ", " + manaCost);
        appendStateInfo(sb);

        return sb.toString();
    }
}
