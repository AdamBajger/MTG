package cz.mtg.abilities;

import cz.mtg.abilities.abstracts.passive.AbilityPassive;
import cz.mtg.abilities.abstracts.passive.AffectsDestroying;
import cz.mtg.cards.Card;
import cz.mtg.exceptions.IndestructibleException;

public class IndestructibleAbility extends AbilityPassive implements AffectsDestroying {

    public IndestructibleAbility(Card source) {
        super("indestructible",source.getName() + " is indestructible. ", source);
    }
    @Override
    public void destroy() throws IndestructibleException {
        throw new IndestructibleException(this);
    }
}
