package cz.mtg.abilities;

import cz.mtg.cards.Card;

import java.util.Objects;

public abstract class Ability {
    private final String keyWord;
    private final String effectDescription;
    private final Card source; // this is the card to which this ability is sticked to

    /**
     * Creates a new ability with a source card assigned, keyWord defined and effect description described.
     * Name is not required argument, because some abilities DO NOT have their own keyword (like haste, vigilance, etc.)
     * ---> the keyWord can be empty
     * @param keyWord Keyword for particular ability
     * @param effectDescription Description of what this ability does.
     * @param source source Card containing this ability instance
     */
    public Ability(String keyWord, String effectDescription, Card source) {
        this.keyWord = keyWord;
        this.effectDescription = effectDescription;
        this.source = source;
    }

    /**
     * This is overloaded constructor omitting ability's keyWord
     * @see this#Ability(String, String, Card) original constructor
     */
    public Ability(String effectDescription, Card source) {
        this("", effectDescription, source);
    }

    /**
     * Returns the source card of this ability
     * @return The Card that contains this ability
     */
    public Card getSource() {
        return source;
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
                ", source: " + source +
                '}';
    }
}
