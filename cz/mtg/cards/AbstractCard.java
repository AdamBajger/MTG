package cz.mtg.cards;


import cz.mtg.exceptions.AlreadyTappedOrUntappedException;
import cz.mtg.exceptions.NegativeNotAllowedException;
import cz.mtg.exceptions.RestrictedCounterAmountException;
import cz.mtg.game.CardPlacement;

import cz.mtg.game.Counter;
import cz.mtg.game.CounterType;
import cz.mtg.game.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Abstract class giving you general functionality of a card in general
 * Initially every card starts in exile, as it is outside of the game.
 *
 * ------------------------------
 *  TODO:
 *      Complete the implementation of counters on this card
 *      implement methods with TODO comment
 */
public abstract class AbstractCard implements Card {

    private final String name;
    private final @NotNull Player owner;
    private @NotNull Player controller;
    private boolean tapped;
    private boolean facedDown;
    private HashMap<CounterType, Counter> counters;
    private @NotNull CardPlacement cardPlacement = CardPlacement.EXILE; // initially card is in exile, outside of the game


    /**
     * This constructor takes name and sticks it to the card
     * also an owner is defined in the card, with controller along the way
     * @param name Name for the newly constructed card
     * @param owner Owner of the Card
     */
    public AbstractCard(String name, Player owner) {
        this.name = name;
        this.owner = owner;
        this.controller = owner;
    }

    public String getName() {
        return name;
    }
    @NotNull
    public Player getOwner() {
        return owner;
    }
    @NotNull
    public Player getController() {
        return controller;
    }
    public void setController(Player controller) {
        this.controller = controller;
    }

    public boolean isTapped() {
        return tapped;
    }
    public void tap() throws AlreadyTappedOrUntappedException {
        if(tapped) throw new AlreadyTappedOrUntappedException(true);
        tapped = true;
    }
    public void untap() throws AlreadyTappedOrUntappedException {
        if(!tapped) throw new AlreadyTappedOrUntappedException(false);
        tapped = false;
    }

    public boolean isFacedDown() {
        return facedDown;
    }
    public void flip() {
        facedDown = !facedDown;
    }

    @NotNull
    @Override
    public CardPlacement getCardPlacement() {
        return cardPlacement;
    }

    /**
     * takes a note about where the card already is
     * It is used by particular public methods of this class
     * It should not be used directly
     * Use this to keep track of where the card is
     * @param cardPlacement Where you want to put the card
     */
    protected void setCardPlacement(@NotNull CardPlacement  cardPlacement) {
        this.cardPlacement = cardPlacement;
    }

    private void removeCardFromPreviousZone() {
        //SIDEBOARD, LIBRARY, HAND, STACK, BATTLEFIELD, GRAVEYARD, EXILE, COMMAND_ZONE;
        switch(getCardPlacement()) {
            case SIDEBOARD:

        }
    }

    @Override
    public void defaultPutCounters(CounterType cType, int amount) throws NegativeNotAllowedException {
        counters.get(cType).addAmount(amount);
    }
    @Override
    public void defaultRemoveCounters(CounterType cType, int amount) throws RestrictedCounterAmountException, NegativeNotAllowedException {
        counters.get(cType).removeAmount(amount);
    }
    @Override
    public int getCounterAmount(CounterType cType) {
        return 0;
    }

    /**
     * Card is cleared of counters, sickness, tap/flip values, power, toughness, etc.
     * Generally the card becomes inactive and loses all abilities
     * use thgis when you destroy card or exile it
     */
    private void clear() {
        this.facedDown = false;
        this.tapped = false;
        this.counters.clear();
    }

    @Override
    public void shuffleIntoLibrary() {
        setCardPlacement(CardPlacement.LIBRARY);
        getOwner().getLibrary().putCardOnTop(this);
        getOwner().getLibrary().shuffle();
    }

    @Override
    public void putOnTopOfLibrary() {
        //TODO
    }

    @Override
    public void putOnBottomOfLibrary() {
        //TODO
    }

    @Override
    public void exile() {
        setCardPlacement(CardPlacement.EXILE);
        // By default, all cards are exiled face-up, rule 406.3
        this.clear();

    }

    @Override
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
        if(isFacedDown()) {
            sb.append(", faced down");
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
