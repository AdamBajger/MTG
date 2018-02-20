/*
 * Copyright (c) 2017. This piece of art or work is owned by me and I do not allow you to do with it anything
  * that would violate the idea with which I created it.
  * If you are unsure if what you are going to do is okay, contact me first on kyrilcouda@gmail.com
 */

package cz.mtg.cards;

import cz.mtg.abilities.abstracts.Ability;
import cz.mtg.abilities.IndestructibleAbility;
import cz.mtg.abilities.abstracts.passive.AffectsTargeting;
import cz.mtg.exceptions.*;
import cz.mtg.game.*;
import cz.mtg.game.targets.Targetable;

import cz.mtg.game.Source;
import java.util.List;
import java.util.Set;

/**
 * This interface tells you which methods you need in a card
 *
 * ================= Default Method System ===================================
 * There are some default methods as {@link Card#destroy() destroy()} or {@link Card#getCardColors() getCardColors()}, etc.,
 * which are here to call their "more default" version, which is implemented in AbstractCard
 * For example:
 *  method destroy() by default only calls method defaultDestroy()
 *      destroy() ---> defaultDestroy()
 *
 *  the same is for method getCardColors()
 *      {@link Card#getCardColors() getCardColors()} ---> {@link Card#defaultGetCardColors() defaultGetCardColors()}
 *
 *  there are (or will be) more methods like this. The reason is following:
 *      We need to have some specific creature cards to be for example indestructible,
 *      with this system of methods, we can just implement interface {@link IndestructibleAbility IndestructibleAbility}.
 *      Now, IndestructibleAbility interface extends this, CardInterface, interface
 *      and overrides the default {@link Card#destroy() destroy()} method
 *      with different {@link IndestructibleAbility#destroy() destroy()} method which,
 *      instead of actually destroying the creature, throws an {@link IndestructibleException exception}.
 *      That will ensure, that the creature can not be destroyed by casual "destroying".
 * ----------------------------------
 * TODO
 *  make all reasonable methods overridable
 *
 */
public interface Card extends Targetable, Source {

    String getName();
    Player getOwner();
    Player getController();
    void setController(Player controller);

    /**
     * According to MTG rules, if a card is in graveyard, hand, library or defaultExile,
     * it can NOT be tapped or untapped. When in defaultExile, it even loses all abilities.
     * If the card is not on the battlefield, the value should be false
     * @return true if card is tapped, false otherwise
     */
    boolean isTapped();

    /**
     * Makes card TAPPED where it is tapped or not
     */
    void tap() throws AlreadyTappedOrUntappedException;

    /**
     * Makes card UNtapped where it is tapped or not
     */
    void untap() throws AlreadyTappedOrUntappedException;

    /**
     * Tells you if the card is faced down (like hidden for players)
     * @return true if card is flipped, false otherwise
     */
    boolean isFacedDown();

    /**
     * flips a card
     */
    void flip();

    /**
     * Casual default method to return set of colors this card inherits
     * @return Set of colors
     */
    Set<Color> defaultGetCardColors();

    /**
     * This is the overridable method to get you colors of a card
     * Reason for this split is for example ability called "devoid", which tells you that a card is colorless
     * independently on its mana cost colors
     * @return Set of colors
     */
    default Set<Color> getCardColors() {
        return defaultGetCardColors();
    }

    /**
     * Returns the linked list of abilities this card has
     * @return LinkedList of Abilities
     */
    List<Ability> getAbilities();

    /**
     * Adds an ability to the list of abilities the card already has
     * @param ability ability to be added
     */
    void addAbility(Ability ability);

    boolean removeAbility(Ability ability);

    /**
     * Just a getter that tells you where the card is
     * @return where the card currently is
     */
    CardPlacement getCardPlacement();

    /**
     * Just targets a target. Here the targetting is checked.
     * If you have an ability that targets anything, you must use this to target it,
     * then apply effects...
     * @param target target being targeted
     * @return target, if everything is fine
     * @throws InvalidTargetException if target is invalid
     */
    default Targetable target(Targetable target) throws InvalidTargetException {
        if(target.canBeTargetOfSource(this)) {
            return target;
        } else throw new InvalidTargetException("Target cannot be targetted by this card.", target, this);
    }

    @Override
    default boolean canBeTargetOfSource(Source source) {
        // checks if any ability affects targeting routine
        // or specifies that the crad cannot be the target of something
        for(Ability a : getAbilities()) {
            if(a instanceof AffectsTargeting) {
                // the alternative method is called
                if(!((AffectsTargeting) a).canBeTargetOfSource(source)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void clear();



    void setCardPlacement(CardPlacement placement);


    /**
     * This is the default method for putting a counter on a card
     * @param cType the type of the Counter put
     * @param amount the amount of counters to put
     */
    void defaultPutCounters(CounterType cType, int amount) throws NegativeNotAllowedException;

    /**
     * This is an overridable method for putting some amount of counters on a card
     * @param cType Type of the Counter put
     * @param amount Amount of counters put
     */
    default void putCounters(CounterType cType, int amount) {
        defaultPutCounters(cType, amount);
    }

    /**
     * This is the default method for removing a counter from a card
     * @param cType the type of the Counter removed
     * @param amount the amount of counters to remove
     */
    void defaultRemoveCounters(CounterType cType, int amount) throws RestrictedCounterAmountException, NegativeNotAllowedException;

    /**
     * This is an overridable method for removing some amount of counters from a card
     * @param cType Type of the Counter removed
     * @param amount Amount of counters to remove
     */
    default void removeCounters(CounterType cType, int amount) throws RestrictedCounterAmountException, NegativeNotAllowedException {
        defaultRemoveCounters(cType, amount);
    }

    /**
     * This is a simple getter for getting the amount of counters of a given type on this card
     * i think this wil NOT be modified by any ability, thus no need for splitting into default and overridable method
     * @param cType given type of a Counter
     * @return amount of counters present
     */
    int getCounterAmount(CounterType cType);

    /**
     * Cards are the same if the names are the same
     * @param c compared card
     * @return true if those cards are identical in the game rules manner
     */
    default boolean sameAs(Card c) {
        return getName().equals(c.getName());
    }
}
