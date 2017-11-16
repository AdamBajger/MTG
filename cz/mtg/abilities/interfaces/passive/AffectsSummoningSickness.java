package cz.mtg.abilities.interfaces.passive;

/**
 * This ability is probably here just because Haste ability
 */
public interface AffectsSummoningSickness {
    /**
     * This method is used instead of default way to apply summoning sickness
     * @return True if the creature should get summoning sickness, false otherwise
     */
    boolean summoningSicknessApplies();
}
