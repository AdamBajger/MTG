package cz.mtg.cards;


import cz.mtg.exceptions.AlreadyTappedOrUntappedException;
import cz.mtg.exceptions.InvalidActionException;
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
    public AbstractCard(String name, @NotNull Player owner) {
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

    /**
     * This method removes a card from current zone based on {@code cardPlacement} attribute
     * call this method every time you move card from somewhere to somewhere.
     *
     * CAUTION:
     * If you call this method and do not move the card, the card will disappear!
     */
    private void removeCardFromPreviousZone() {
        //SIDEBOARD, LIBRARY, HAND, STACK, BATTLEFIELD, GRAVEYARD, EXILE, COMMAND_ZONE;
        switch(getCardPlacement()) {
            case SIDEBOARD:
                // TODO:
                // remove from its owners sideboard
                // I don't thing this case will happen at any point
                throw new RuntimeException("For some fuckin' sake we had to remove card from sideboard!");
            case LIBRARY:
                if(!getOwner().getLibrary().removeCard(this)) {
                    throw new RuntimeException("Tried to move card from library but Card is not in the library.");
                }
                break;
            case HAND:
                if(!getOwner().getHand().remove(this)) {
                    throw new RuntimeException("Tried to move card from hand but Card is not in hand.");
                }
                break;
            case STACK:
                // not required here, cards are removed from stack automatically, or by ability effects
                break;
            case BATTLEFIELD:
                if(!getController().getGameAssigned().getBattlefield().remove(this)) {
                    throw new RuntimeException("Tried to move card from battlefield but Card is not on battlefield.");
                }
                break;
            case GRAVEYARD:
                if(!getOwner().getGraveyard().remove(this)) {
                    throw new RuntimeException("Tried to move card from graveyard but Card is not in graveyard.");
                }
                break;
            case EXILE:
                if(!getController().getGameAssigned().getExile().remove(this)) {
                    throw new RuntimeException("Tried to move card from exile but Card is not in exile.");
                }
                break;
            case COMMAND_ZONE:
                if(!getController().getGameAssigned().getCommandZone().remove(this)) {
                    throw new RuntimeException("Tried to move card from command zone but Card is not there.");
                }
                break;
            default:
                // If we got here, then the card was not in a known game zone, or some other error --> throw Runtime
                throw new RuntimeException("Zone previously containing Card not recognized!");
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
        return counters.get(cType).getAmount();
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
        putOnTopOfLibrary(); // puts Card on top of library, clears card and removes it from previous zone
        getOwner().getLibrary().shuffle(); // the method says SHUFFLE into... so
    }

    @Override
    public void putOnTopOfLibrary() {
        clear(); // card loses abilities and counters
        removeCardFromPreviousZone(); // remove the card from previous zone
        getOwner().getLibrary().putCardOnTop(this); // put the card to the new zone
        setCardPlacement(CardPlacement.LIBRARY); // keep track of where the card is
    }

    @Override
    public void putOnBottomOfLibrary() {
        clear(); // card loses abilities and counters
        removeCardFromPreviousZone(); // remove the card from previous zone
        getOwner().getLibrary().putCardOnBottom(this); // put the card to the new zone
        setCardPlacement(CardPlacement.LIBRARY); // keep track of where the card is
    }

    @Override
    public void exile() {
        // By default, all cards are exiled face-up, rule 406.3
        clear(); // card loses abilities and counters
        removeCardFromPreviousZone(); // remove the card from previous zone
        getController().getGameAssigned().getExile().add(this); // put the card to the new zone
        setCardPlacement(CardPlacement.EXILE); // keep track of where the card is
    }

    @Override
    public void defaultDestroy() {
        clear(); // card loses abilities and counters
        removeCardFromPreviousZone(); // remove the card from previous zone
        getOwner().getGraveyard().push(this);
        setCardPlacement(CardPlacement.GRAVEYARD);
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
