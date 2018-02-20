/*
 * Copyright (c) 2017. This piece of art or work is owned by me and I do not allow you to do with it anything that would violate the idea with which I created it. If you are unsure if what you are going to do is okay, contact me first on kyrilcouda@gmail.com
 */

package cz.mtg.game;

public interface Stackable {
    /**
     * Gets you a source controller
     * @return source controller
     */
    Player getController();

    /**
     * This will return the conditions that have to be satisfied in a human readable manner
     * For example for Harrow, it would return "As an additional cost to cast Harrow, sacrifice a land."
     * @return Text-written conditions for casting this spell
     */
    String getEffectDescription();

    /**
     * This method contains what happens when a card or ability resolves.
     * That means, what happens if the card is on the TOP of the STACK and every player passed its priority
     * without doing anything.
     * For most Cards (excepts Instants or Sorceries) it means just putting the card on the battlefield
     * but for abilities and, earlier excluded, instants and sorceries, it means the whole card functionality
     */
    void resolve();

    /**
     * this puts the spell on stack
     */
    default void putOnStack(Stackable c) {
        getController().getGameAssigned().getSpellStack().put(c);
    }
}
