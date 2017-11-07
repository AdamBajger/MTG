package cz.mtg.cards;

import cz.mtg.cards.castable.CastableCardInterface;
import cz.mtg.exceptions.AlreadyTappedOrUntappedException;
import cz.mtg.exceptions.IndestructibleException;
import cz.mtg.exceptions.NegativeNotAllowedException;
import cz.mtg.exceptions.RestrictedCounterAmountException;
import cz.mtg.game.CardPlacement;
import cz.mtg.game.Color;
import cz.mtg.game.CounterType;
import cz.mtg.game.Player;

import java.util.Set;

/**
 * This interface tells you which methods you need in a card
 *
 * ================= Default Method System ===================================
 * There are some default methods as {@link CardInterface#destroy() destroy()} or {@link CastableCardInterface#getCardColors() getCardColors()}, etc.,
 * which are here to call their "more default" version, which is implemented in AbstractCard
 * For example:
 *  method destroy() by default only calls method defaultDestroy()
 *      destroy() ---> defaultDestroy()
 *
 *  the same is for method getCardColors()
 *      {@link CastableCardInterface#getCardColors() getCardColors()} ---> {@link CastableCardInterface#defaultGetCardColors() defaultGetCardColors()}
 *
 *  there are (or will be) more methods like this. The reason is following:
 *      We need to have some specific creature cards to be for example indestructible,
 *      with this system of methods, we can just implement interface {@link cz.mtg.abilities.IndestructibleAbility IndestructibleAbility}.
 *      Now, IndestructibleAbility interface extends this, CardInterface, interface
 *      and overrides the default {@link CardInterface#destroy() destroy()} method
 *      with different {@link cz.mtg.abilities.IndestructibleAbility#destroy() destroy()} method which,
 *      instead of actually destroying the creature, throws an {@link IndestructibleException exception}.
 *      That will ensure, that the creature can not be destroyed by casual "destroying".
 * ----------------------------------
 * TODO
 *  make all reasonable methods overridable
 *
 */
public interface CardInterface {

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
     * According to MTG rules, if a card is in graveyard, hand, library or exile,
     * it can NOT be tapped or untapped. When in exile, it even loses all abilities.
     * If the card is not on the battlefield, the value should be false
     * @return true if card is flipped, false otherwise
     */
    boolean isFacedDown();

    /**
     * flips a card
     */
    void flip();

    /**
     * Just a getter
     * @return where the card currently is
     */
    CardPlacement getCardPlacement();

    /**
     * Shuffles this card into library
     * -->  that means it places card somewhere into a its owners library (top is the fastest)
     *      and then shuffles the Library
     */
    void shuffleIntoLibrary();

    /**
     * Puts this card on top of its owners library
     * Top = beginning of the LinkedList library
     */
    void putOnTopOfLibrary();

    /**
     * Puts this card on the bottom of its owners library
     * Bottom = end of the LinkedList library
     */
    void putOnBottomOfLibrary();

    /**
     * Exiles this card.
     * Card is placed to EXILE and its attributes are nulled
     * Consider changing access to protected - so we will have to access this method
     * only from subclasses, i. e. particular instances of this class
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
     * this method is overridden for example by {@link cz.mtg.abilities.IndestructibleAbility} interface
     * to make the unit indestructible
     * @throws IndestructibleException
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
