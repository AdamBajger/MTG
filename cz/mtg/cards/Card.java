package cz.mtg.cards;

import cz.mtg.abilities.AbilityStackable;
import cz.mtg.abilities.IndestructibleAbility;
import cz.mtg.exceptions.AlreadyTappedOrUntappedException;
import cz.mtg.exceptions.IndestructibleException;
import cz.mtg.exceptions.NegativeNotAllowedException;
import cz.mtg.exceptions.RestrictedCounterAmountException;
import cz.mtg.game.*;

import java.util.LinkedList;
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
public interface Card {

    String getName();
    Player getOwner();
    Player getController();
    void setController(Player controller);

    /**
     * According to MTG rules, if a card is in graveyard, hand, library or exile,
     * it can NOT be tapped or untapped. When in exile, it even loses all abilities.
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
    LinkedList<AbilityStackable> getAbilities();

    /**
     * Just a getter that tells you where the card is
     * @return where the card currently is
     */
    CardPlacement getCardPlacement();

    /**
     * Shuffles this card into library
     * -->  that means it places card somewhere into a its owners library (top is the fastest)
     *      and then shuffles the Library
     *      Also the cardPlacement attribute is changed to match game zone
     */
    void shuffleIntoLibrary();

    /**
     * Puts this card on top of its owners library
     * Top = beginning of the LinkedList library
     * Also the cardPlacement attribute is changed to match game zone
     */
    void putOnTopOfLibrary();

    /**
     * Puts this card on the bottom of its owners library
     * Bottom = end of the LinkedList library
     * Also the cardPlaycement attribute is changed to match game zone
     */
    void putOnBottomOfLibrary();

    /**
     * Exiles this card.
     * Card is placed to EXILE and its attributes are nulled
     * Also the cardPlacement attribute is changed to match game zone
     */
    void exile();

    /**
     * This is the default destroy procedure, implemented by AbstractCard
     * places card to GRAVEYARD and clears the card (it loses all abilities)
     * This method is by default called by destroy() method
     */
    void defaultDestroy();

    /**
     * This is also default method. Unlike the defaultDestroy() method,
     * this method is overridden for example by {@link IndestructibleAbility} interface
     * to make the unit indestructible
     * @throws IndestructibleException if the creature is indestructible
     */
    default void destroy() throws IndestructibleException {
        this.defaultDestroy();
    }

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
    default void putCounters(CounterType cType, int amount) throws NegativeNotAllowedException {
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
}
