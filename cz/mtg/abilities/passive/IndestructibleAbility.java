package cz.mtg.abilities.passive;

import cz.mtg.exceptions.IndestructibleException;
import cz.mtg.cards.Card;

public interface IndestructibleAbility extends Card {

    @Override
    default void destroy() throws IndestructibleException {
        throw new IndestructibleException(this);
    }
}
