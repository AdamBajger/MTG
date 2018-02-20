package cz.mtg.game.targets;

import cz.mtg.game.Source;

/**
 * This interface gives you methods for target evaluation
 * Not sure how I planned to do this, but i will leave it there for a while
 *
 * This class serves as a markup for entities that are being targetable
 */
public interface Targetable {
    /**
     * This method tells you if this targetable entity can be a valid target of a source
     * @param source
     * @return
     */
    boolean canBeTargetOfSource(Source source);


}
