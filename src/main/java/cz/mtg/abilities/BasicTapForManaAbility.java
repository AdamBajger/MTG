package cz.mtg.abilities;

import cz.mtg.abilities.abstracts.active.TapForManaAbility;
import cz.mtg.cards.Card;
import cz.mtg.exceptions.InvalidActionException;
import cz.mtg.game.mana.ManaSet;

public class BasicTapForManaAbility extends TapForManaAbility {

    public BasicTapForManaAbility(Card source, ManaSet cost, ManaSet manaGenerated) {
        super(source, cost, manaGenerated);
    }

    /**
     * This method will execute / satisfy the additional costs
     * Everything that is required to cast desired card/spell will be done
     *
     * @throws InvalidActionException if the requirements can not be satisfied
     */
    @Override
    public void spendAdditiondalManaCost() throws InvalidActionException {

    }
}
