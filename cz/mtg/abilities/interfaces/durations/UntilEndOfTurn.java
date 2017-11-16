package cz.mtg.abilities.interfaces.durations;

/**
 * This interface MUST be implemented by all abilities, that have effects, which last until end of turn.
 */
public interface UntilEndOfTurn {
    /**
     * This method clears all effects of this particular ability, that should last only until end of turn
     */
    void cleanup();
}
