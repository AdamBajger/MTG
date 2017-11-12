package cz.mtg.abilities;

import cz.mtg.abilities.interfaces.passive.Destroying;
import cz.mtg.exceptions.IndestructibleException;

public class IndestructibleAbility extends AbilityPassive implements Destroying {

    @Override
    public void destroy() throws IndestructibleException {
        throw new IndestructibleException(this.getSource());
    }
}
