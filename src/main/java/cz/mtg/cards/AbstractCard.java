package cz.mtg.cards;



import cz.mtg.game.Source;
import cz.mtg.abilities.abstracts.Ability;
import cz.mtg.abilities.abstracts.passive.AffectsTargeting;
import cz.mtg.exceptions.*;
import cz.mtg.game.*;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Abstract class giving you general functionality of a card in general
 * Initially every card starts in defaultExile, as it is outside of the game.
 *
 * ------------------------------
 *
 */
public abstract class AbstractCard implements Card {

    private final String name;
    private final Player owner;
    private Player controller;
    private boolean tapped;
    private boolean facedDown;
    private final HashMap<CounterType, Counter> counters = new HashMap<>();
    private @NotNull CardPlacement cardPlacement = CardPlacement.INIT; // initially card is in defaultExile, outside of the game
    private final LinkedList<Ability> abilities = new LinkedList<>();


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
    public Player getOwner() {
        return owner;
    }
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

    @Override
    public List<Ability> getAbilities() {
        return Collections.unmodifiableList(abilities);
    }

    @Override
    public void addAbility(Ability ability) {
        abilities.addFirst(ability);
    }

    @Override
    public boolean removeAbility(Ability ability) {
        return abilities.remove(ability);
    }


    @Override
    public boolean canBeTargetOfSource(Source source) {
        for(Ability ability : getAbilities()) {
            // check for abilities that affect targeting
            if(ability instanceof AffectsTargeting) {
                if(!((AffectsTargeting)ability).canBeTargetOfSource(source)) {
                    // if a card can not be a target of the source
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * takes a note about where the card already is
     * It is used by particular public methods of this class
     * It should not be used directly
     * Use this to keep track of where the card is
     * @param cardPlacement Where you want to put the card
     */
    @Override
    public void setCardPlacement(@NotNull CardPlacement cardPlacement) {
        this.cardPlacement = cardPlacement;
    }

    @NotNull
    @Override
    public CardPlacement getCardPlacement() {
        return this.cardPlacement;
    }


    @Override
    public void defaultPutCounters(CounterType cType, int amount) {
        if(counters.containsKey(cType)) {
            try {
                counters.get(cType).changeAmount(amount);
            } catch(NegativeNotAllowedException|RestrictedCounterAmountException e) {
                throw new RuntimeException("It crashed somehow on putting a counter.", e);
            }
        } else {
            counters.put(cType, new Counter(amount, cType));
        }
    }
    @Override
    public void defaultRemoveCounters(CounterType cType, int amount) throws RestrictedCounterAmountException {
        if(counters.containsKey(cType)) {

            if(counters.get(cType).getAmount() - amount == 0) {
                counters.remove(cType);
                return;
            }
            counters.get(cType).changeAmount(-amount);
        } else {
            throw new InvalidActionException("No such counters: " + cType);
        }

    }
    @Override
    public int getCounterAmount(CounterType cType) {
        Counter c = counters.get(cType);
        // check if the counter is even there to avoid getting NullPointerException later
        if(c == null) {
            return 0;
        } else return counters.get(cType).getAmount();
    }

    /**
     * Card is cleared of counters, sickness, tap/flip values, power, toughness, etc.
     * Generally the card becomes inactive and loses all abilities
     * use thgis when you destroy card or defaultExile it
     */
    @Override
    public void clear() {
        this.facedDown = false;
        this.tapped = false;
        this.counters.clear();
    }







    /**
     * This method is used in the toString() methods of AbstractCard subclasses to reduce redundancy
     * it appends basic info about card name and other important things to a given StringBuilder
     * @param sb given StringBuilder
     */
    protected void appendBasicInfo(StringBuilder sb) {
        sb.append(this.getClass().getName()).append(" {\nname: ").append(name).append(", ");
    }

    /**
     * This method is used in the toString() methods of AbstractCard subclasses to reduce redundancy
     * it appends state info about card state to a given StringBuilder
     * @param sb given StringBuilder
     */
    protected void appendStateInfo(StringBuilder sb) {

        sb.append("stateInfo: {\n" +
                "tapped: ");
        if(isTapped()) {
            sb.append("true");
        } else {
            sb.append("false");
        }
        sb.append(", facedDown: ");
        if(isFacedDown()) {
            sb.append("true");
        } else {
            sb.append("false");
        }
        sb.append(", cardPlacement: ").append(getCardPlacement());
        sb.append(", counters: {");
        for (CounterType cType : counters.keySet()) {
            sb.append(cType).append(": ").append(counters.get(cType)).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length()); // to delete the last ", " from loop above

        sb.append("\n" +
                "}");

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
