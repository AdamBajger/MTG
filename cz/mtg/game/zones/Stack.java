package cz.mtg.game.zones;

import cz.mtg.exceptions.NotOnStackException;
import cz.mtg.game.Stackable;

import java.util.LinkedList;

/**
 * this class implements spell stack
 * When you cast a card, it goes to the top of the stack.
 * Whenever a card goes to the stack, each player receives priority (that means he can react, do something)
 * in a successive order (1st player, then 2nd, then 3rd, etc.) and ONLY after every player pass the priority
 * without doing anything, the stack resolves. After every player passed priority without actions,
 * the top card on the stack resolves.
 *
 * If during the resolving a new ability is put on the stack, the cycle repeats and every player receives priority.
 *
 * Playing lands:
 * 305.1. A player who has priority may play a land card from his or her hand during a main phase of his
 * or her turn when the stack is empty. Playing a land is a special action; it doesn’t use the stack (see
 * rule 115). Rather, the player simply puts the land onto the battlefield. Since the land doesn’t go on
 * the stack, it is never a spell, and players can’t respond to it with instants or activated abilities.
 * see "MTG Rules 20170925.pdf" in the MTGResources directory
 *
 */
public class Stack {
    private final LinkedList<Stackable> spellQueue = new LinkedList<>();

    /**
     * Appends a Stackable object to the spell queue
     * @param stackable Stackable card, ability or other stackable object
     */
    public void put(Stackable stackable) {
        spellQueue.push(stackable);
    }

    /**
     * Removes spell from the stack
     * @param stackable any stackable object (stackable card or ability)
     * @throws NotOnStackException If the spell is not on stack.
     */
    public void remove(Stackable stackable) throws NotOnStackException {
        if(!spellQueue.remove(stackable)) {
            throw new NotOnStackException();
        }
    }

    public boolean isEmpty() {
        return spellQueue.isEmpty();
    }

    /**
     * This will take the top card on the stack and applies its effects that were assigned by its casting
     * this will refresh other players' priority and everyone can react
     */
    public void resolveNext() {
        spellQueue.pop().resolve();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Stack: {");
        for(Stackable stackable : spellQueue) {
            sb.append('\n').append("spell: ").append(stackable.toString());
        }
        sb.append('\n').append("}").append('\n');
        return sb.toString();
    }
}
