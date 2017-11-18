package cz.mtg.abilities;

import cz.mtg.cards.Card;
import cz.mtg.exceptions.AlreadyTappedOrUntappedException;
import cz.mtg.exceptions.InsufficientManaException;
import cz.mtg.game.ConsumesMana;
import cz.mtg.game.Mana;

import java.util.Set;

/**
 * This interface describes ability of a land to be tapped for mana
 * Also abilities of creatures and artifacts that generate mana are involved here
 * Every permanent card that gives you mana by tapping has this ability
 */
public class TapForManaAbility extends Ability implements ConsumesMana {
    private Set<Mana> cost;

    /**
     * Creates an instance of this ability
     * @param source Card to which it is sticked to
     */
    public TapForManaAbility(Card source, Set<Mana> cost) {
        super("add ?mana? to your mana pool.", source);
        this.cost = cost;
    }

    @Override
    public Set<Mana> getManaCost() {
        return null;
    }

    /**
     * This ability does not use stack
     * No one can respond to this action, this action thus cannot be countered
     */
    public void tapForMana() throws AlreadyTappedOrUntappedException, InsufficientManaException {
        if(notEnoughMana(this)) throw new InsufficientManaException();
        getSource().tap();
        getSource().getController().getManaPool().spendMana(cost);
    }

}
