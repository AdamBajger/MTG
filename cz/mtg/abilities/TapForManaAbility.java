package cz.mtg.abilities;

import cz.mtg.cards.Card;
import cz.mtg.exceptions.AlreadyTappedOrUntappedException;
import cz.mtg.exceptions.InsufficientManaException;
import cz.mtg.game.Mana;
import cz.mtg.game.ManaCollection;

import java.util.Set;

/**
 * This interface describes ability of a land to be tapped for mana
 * Also abilities of creatures and artifacts that generate mana are involved here
 * Every permanent card that gives you mana by tapping has this ability
 */
public class TapForManaAbility extends Ability {
    private Set<Mana> cost;

    /**
     * Creates an instance of this ability
     * @param source Card to which it is sticked to
     */
    public TapForManaAbility(Card source, Set<Mana> cost) {
        super("add ?mana? to your mana pool.", source);
        this.cost = cost;
    }

    /**
     * This method checks if a given player has enough mana to cast this
     * The casting player is always the owner of the casted card or owner of the casting source
     * @return True if there is enough mana. False otherwise
     */
    boolean enoughMana() {
        ManaCollection checkedManaPool = getSource().getController().getManaPool();

        // for each mana color in manaCost, check if there is enough mana of that color in checkedManaPool
        for(Mana m : cost) {
            if(!(checkedManaPool.getManaOfColorAmount(m.getColor()) >= m.getAmount())) {
                return false;
            }
        }
        // no insufficient mana found ---> return true
        return true;
    }

    /**
     * This ability does not use stack
     * No one can respond to this action, this action thus cannot be countered
     */
    public void tapForMana() throws AlreadyTappedOrUntappedException, InsufficientManaException {
        if(!enoughMana()) throw new InsufficientManaException();
        getSource().tap();
        getSource().getController().getManaPool().spendMana(cost);
    }

}
