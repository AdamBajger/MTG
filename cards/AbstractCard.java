package cz.mtg.cards;


import cz.mtg.exceptions.AlreadyTappedOrUntappedException;
import cz.mtg.exceptions.NegativeNotAllowedException;
import cz.mtg.exceptions.RestrictedCounterAmountException;
import cz.mtg.game.CardPlacement;

import cz.mtg.game.Counter;
import cz.mtg.game.CounterType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Abstract class giving you general functionality of a card in general
 * Initially every card starts in exile, as it is outside of the game.
 *
 * ------------------------------
 *  TODO:
 *      Complete the implementation of counters on this card
 */
public abstract class AbstractCard implements CardInterface {

    private String name;
    private boolean tapped;
    private boolean flipped;
    private HashMap<CounterType, Counter> counters;
    private @NotNull CardPlacement cardPlacement = CardPlacement.EXILE; // initially card is in exile, outside of the game


    /**
     * This constructor just takes name and sticks it to the card
     * Now your card has a name, yay!
     * @param name Name for the newly constructed card
     */
    public AbstractCard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isTapped() {
        return tapped;

    }

    public boolean isFlipped() {
        return flipped;
    }

    @NotNull
    public CardPlacement getCardPlacement() {
        return cardPlacement;
    }

    public void tap() throws AlreadyTappedOrUntappedException {
        if(tapped) throw new AlreadyTappedOrUntappedException(true);
        tapped = true;
    }

    public void untap() throws AlreadyTappedOrUntappedException {
        if(!tapped) throw new AlreadyTappedOrUntappedException(false);
        tapped = false;
    }

    public void flip() {
        flipped = !flipped;
    }

    @Override
    public void defaultPutCounters(CounterType cType, int amount) throws NegativeNotAllowedException {
        if(counters.containsKey(cType)) {
            counters.get(cType).addAmount(amount);
        } else {
            counters.putIfAbsent(cType, new Counter(amount, cType));
        }
    }


    @Override
    public void defaultRemoveCounters(CounterType cType, int amount) throws RestrictedCounterAmountException, NegativeNotAllowedException {
        if(counters.containsKey(cType)) {
            counters.get(cType).removeAmount(amount);
            // if the number of counters reaches zero, remove them
            if(counters.get(cType).getAmount() == 0) {
                counters.remove(cType);
            }
        }
    }

    @Override
    public int getCounterAmount(CounterType cType) {
        return counters.get(cType).getAmount();
    }

    /**
     * places a card where you want to place it
     * @param cardPlacement Where you want to put the card
     */
    protected void setCardPlacement(@NotNull CardPlacement  cardPlacement) {
        this.cardPlacement = cardPlacement;
    }

    /**
     * Card is cleared of counters, sickness, tap/flip values, power, toughness, etc.
     * Generally the card becomes inactive and loses all abilities
     */
    private void clear() {
        this.flipped = false;

    }


    public void exile() {
        setCardPlacement(CardPlacement.EXILE);
        this.clear();

    }

    /**
     * This method is supposed to destroy the card
     * Basically it puts the card into graveyard and sets unnecessary attributes to null
     * If the card
     */

    public void defaultDestroy() {
        this.setCardPlacement(CardPlacement.GRAVEYARD);
        System.out.println("Destroyed...");
    }

    /**
     * This method is used in the toString() methods of AbstractCard subclasses to reduce redundancy
     * it appends basic info about card name and other important things to a given StringBuilder
     * @param sb
     */
    protected void appendBasicInfo(StringBuilder sb) {
        sb.append(this.getClass().getName()).append(" {").append(name);
    }

    /**
     * This method is used in the toString() methods of AbstractCard subclasses to reduce redundancy
     * it appends state info about card state to a given StringBuilder
     * @param sb
     */
    protected void appendStateInfo(StringBuilder sb) {
        if(isTapped()) {
            sb.append(", tapped");
        } else {
            sb.append(", untapped");
        }
        if(isFlipped()) {
            sb.append(", flipped face down");
        }
        sb.append(", card is in ").append(getCardPlacement());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // append basic info
        appendBasicInfo(sb);
        // append info about card state and position
        appendStateInfo(sb);
        sb.append('}');
        return sb.toString();
    }
}
