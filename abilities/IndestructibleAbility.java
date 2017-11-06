package cz.mtg.abilities;

import cz.mtg.exceptions.IndestructibleException;
import cz.mtg.cards.CardInterface;

public interface IndestructibleAbility extends CardInterface {

    @Override
    default void destroy() throws IndestructibleException {
        throw new IndestructibleException(this);
    }
}
