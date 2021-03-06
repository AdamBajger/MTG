package cz.mtg.cards.lands.basic;

import cz.mtg.abilities.BasicTapForManaAbility;
import cz.mtg.cards.Card;
import cz.mtg.cards.lands.AbstractLandCard;
import cz.mtg.game.Color;
import cz.mtg.game.Player;
import cz.mtg.game.mana.ManaSet;
import cz.mtg.game.mana.RepresentativeMana;

/**
 * This represents a basic land card
 */
public abstract class AbstractBasicLandCard extends AbstractLandCard {

    /**
     * Basic constructor for a basic land card
     * @param name gives the card a name
     * @param owner sets the owner of the card
     * @param manaColor tell you which mana is generated by this basic land card
     */
    public AbstractBasicLandCard(String name, Player owner, Color manaColor) {
        super(name, owner);
        ManaSet cost = new ManaSet();
        ManaSet generated = new ManaSet();
        generated.add(new RepresentativeMana(manaColor, 1));
        addAbility(new BasicTapForManaAbility(this, cost, generated));
    }


}
