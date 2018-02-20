package cz.mtg.abilities.abstracts;

import cz.mtg.game.Source;
import cz.mtg.cards.Card;
import cz.mtg.game.Player;

import java.util.Objects;

public abstract class Ability implements Source {
    private final String keyWord;
    private final String effectDescription;
    private final Card sourceCard; // this is the card to which this ability is sticked to

    /**
     * Creates a new ability with a sourceCard card assigned, keyWord defined and effect description described.
     * Name is not required argument, because some abilities DO NOT have their own keyword (like haste, vigilance, etc.)
     * ---> the keyWord can be empty
     * @param keyWord Keyword for particular ability
     * @param effectDescription Description of what this ability does.
     * @param sourceCard sourceCard Card containing this ability instance
     */
    public Ability(String keyWord, String effectDescription, Card sourceCard) {
        this.keyWord = keyWord;
        this.effectDescription = effectDescription;
        this.sourceCard = sourceCard;
    }

    /**
     * This is overloaded constructor omitting ability's keyWord
     * @see this#Ability(String, String, Card) original constructor
     */
    public Ability(String effectDescription, Card sourceCard) {
        this("", effectDescription, sourceCard);
    }

    public Card getSourceCard() {
        return sourceCard;
    }

    @Override
    public String getName() {
        return getTextRepresentation();
    }

    /**
     * Returns the sourceCard card of this ability
     * @return The Card that contains this ability
     */
    public Player getController() {
        return sourceCard.getController();
    }

    public String getEffectDescription() {
        return effectDescription;
    }

    public String getTextRepresentation() {
        if(keyWord != null && keyWord.length() > 0) {
            return keyWord + " ";
        } else return effectDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ability)) return false;
        Ability ability = (Ability) o;
        return Objects.equals(keyWord, ability.keyWord) &&
                Objects.equals(getEffectDescription(), ability.getEffectDescription());
    }

    @Override
    public int hashCode() {

        return Objects.hash(keyWord, getEffectDescription());
    }

    @Override
    public String toString() {
        return "Ability: {" +
                "keyWord: '" + keyWord + '\'' +
                ", effectDescription: '" + effectDescription + '\'' +
                ", sourceCard: " + sourceCard +
                '}';
    }
}
