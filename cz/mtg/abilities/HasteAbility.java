package cz.mtg.abilities;


import cz.mtg.abilities.interfaces.passive.AffectsSummoningSickness;
import cz.mtg.cards.Card;

/**
 * Implementation of a Haste ability
 */
public class HasteAbility extends AbilityPassive implements AffectsSummoningSickness {

    public HasteAbility(Card source) {
        super("haste", source.getName() + " is unaffected by summoning sickness", source);
    }

    /**
     * Here you stat that if you want to see if a creature implementing HasteAbility
     * has summoning sickness, it will always return false
     * @return False
     */
    @Override
    public boolean summoningSicknessApplies() {
        return false;
    }
}
