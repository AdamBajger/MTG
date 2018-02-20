/*
 * Copyright (c) 2017. This piece of art or work is owned by me and I do not allow you to do with it anything that would violate the idea with which I created it. If you are unsure if what you are going to do is okay, contact me first on kyrilcouda@gmail.com
 */

package cz.mtg.abilities.abstracts.triggered;

import cz.mtg.abilities.abstracts.Ability;
import cz.mtg.cards.Card;
import cz.mtg.game.Stackable;

/**
 * This ability goes on stack after it is triggered
 *
 * 603.3. Once an ability has triggered, its controller puts it on the stack as an object
 * that’s not a card the next time a player would receive priority. See rule 115, “Timing and Priority.”
 * The ability becomes the topmost object on the stack.
 * It has the text of the ability that created it, and no other characteristics.
 * It remains on the stack until it’s countered, it resolves, or an effect moves it elsewhere.
 */
public abstract class AbilityTriggered extends Ability implements Stackable {
    /**
     * Creates a new ability with a sourceCard card assigned, keyWord defined and effect description described.
     * Name is not required argument, because some abilities DO NOT have their own keyword (like haste, vigilance, etc.)
     * ---> the keyWord can be empty
     *
     * @param keyWord           Keyword for particular ability
     * @param effectDescription Description of what this ability does.
     * @param sourceCard        sourceCard Card containing this ability instance
     */
    public AbilityTriggered(String keyWord, String effectDescription, Card sourceCard) {
        super(keyWord, effectDescription, sourceCard);
    }

    /**
     * This is overloaded constructor omitting ability's keyWord
     *
     * @param effectDescription Description of what this ability does.
     * @param sourceCard        sourceCard Card containing this ability instance
     */
    public AbilityTriggered(String effectDescription, Card sourceCard) {
        super(effectDescription, sourceCard);
    }

    /**
     * Puts this ability on stack
     */
    public void putEffectOnStack() {
        getController().getGameAssigned().getSpellStack().put(this);
    }

    /**
     * executes effects of this ability
     */
    abstract public void resolve();
}
