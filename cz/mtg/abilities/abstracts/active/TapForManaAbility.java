package cz.mtg.abilities.abstracts.active;

import cz.mtg.abilities.abstracts.Ability;
import cz.mtg.cards.Card;
import cz.mtg.exceptions.AlreadyTappedOrUntappedException;
import cz.mtg.exceptions.InsufficientManaException;
import cz.mtg.game.ConsumesMana;
import cz.mtg.game.mana.ManaSet;

/**
 * This interface describes ability of a land to be tapped for mana
 * Also abilities of creatures and artifacts that generate mana are involved here
 * Every permanent card that gives you mana by tapping has this ability
 */
public abstract class TapForManaAbility extends Ability implements ConsumesMana {
    private ManaSet cost;
    private ManaSet manaGenerated;

    /**
     * Creates an instance of this ability
     * @param source Card to which it is sticked to
     */
    public TapForManaAbility(Card source, ManaSet cost, ManaSet manaGenerated) {
        super("add ?mana? to your mana pool.", source);
        this.cost = cost;
        this.manaGenerated =  manaGenerated;
    }

    @Override
    public ManaSet getManaCost() {
        return cost;
    }


    /**
     * This just checks whether the player has enough mana to use this ability
     * and then spends mana cost and then adds the mana to the mana pool
     * This ability does not use stack
     * No one can respond to this action, this action thus cannot be countered
     */
    public void tapForMana() throws AlreadyTappedOrUntappedException, InsufficientManaException {
        if(getSourceCard().getController().notEnoughMana(this)) throw new InsufficientManaException("Not enough Mana");
        getSourceCard().tap();
        getSourceCard().getController().spendMana(cost, this);
        System.out.println();
        getSourceCard().getController().addAllMana(manaGenerated);
    }


}
