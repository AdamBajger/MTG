package cz.mtg.cards.castable.creature;

import cz.mtg.abilities.Ability;
import cz.mtg.abilities.interfaces.passive.AffectsCreatureToughness;
import cz.mtg.cards.castable.AbstractCastableCard;
import cz.mtg.game.CardPlacement;
import cz.mtg.game.CounterType;
import cz.mtg.game.Mana;
import cz.mtg.game.Player;

import java.util.Set;

/**
 * This class is here to allow PRIVATE attributes for creature
 * It will allow us to control how attributes (like power, toughness, summonningSickness, etc.) are modified
 *
 * ----------------------------
 *  TODO:
 *      define appropriate attributes HTV
 *      define appropriate getters
 *      define appropriate setters
 */
public abstract class AbstractCreatureCard extends AbstractCastableCard implements Creature {
    private boolean summoningSickness = true;
    private final int power;
    private int powerModifier = 0;
    private final int toughness;
    private int toughnessModifier = 0;
    private int damageTaken = 0;

    /**
     * Basic constructor, creates a creature card with necessary attributes
     * @param name Name of the creature card
     * @param owner Player who owns that card
     * @param manaCost Mana cost of this creature card
     * @param power Power of the creature
     * @param toughness Toughness of the creature
     */
    public AbstractCreatureCard(String name, Player owner, Set<Mana> manaCost, int power, int toughness) {
        super(name, owner, manaCost);
        this.power = power;
        this.toughness = toughness;
    }

    @Override
    public boolean defaultHasSummoningSickness() {
        return summoningSickness;
    }

    public int getActualPower() {
        int finalPower = power;
        finalPower += getCounterAmount(CounterType.P_T_COUNTER_POSITIVE);
        finalPower -= getCounterAmount(CounterType.P_T_COUNTER_NEGATIVE);
        finalPower += powerModifier;
        return finalPower;
    }

    @Override
    public void modifyPower(int amount) {
        powerModifier += amount;
    }

    public int getActualToughness() {
        int finalToughness = toughness;
        finalToughness += getCounterAmount(CounterType.P_T_COUNTER_POSITIVE);
        finalToughness -= getCounterAmount(CounterType.P_T_COUNTER_NEGATIVE);
        finalToughness += toughnessModifier;

        // Check for abilities that modify toughness
        for(Ability ability : this.getAbilities()) {
            if(ability instanceof AffectsCreatureToughness) {
                // adds the amount of which the ability changes the toughness
                finalToughness += ((AffectsCreatureToughness)ability).getToughnessModifier();
            }
        }
        return finalToughness;
    }

    @Override
    public void modifyToughness(int amount) {
        toughnessModifier += amount;
    }

    /**
     * This method MUST be overridden, if there ARE ADDITIONAL EFFECTS, that trigger when the creature RESOLVES
     * that DOES NOT include effects that trigger when this creature enters battlefield
     * TODO: check rules --> Is effect that triggers when a creature enters battlefield a spell? Is it counterable?
     * @return What happens after this creature resolves (goes to the battlefield)
     */
    @Override
    public String getEffectDescription() {
        return "Puts " + getName() + " on the battlefield.";
    }

    public void clear() {
        super.clear();
        summoningSickness = false;

    }

    /**
     * This method contains what happens when a card or ability resolves.
     * That means, what happens if the card is on the TOP of the STACK and every player passed its priority
     * without doing anything.
     * For most Cards (excepts Instants or Sorceries) it means
     */
    @Override
    public void resolve() {
        getOwner().getGameAssigned().getBattlefield().add(this);
        this.setCardPlacement(CardPlacement.BATTLEFIELD);
        // TODO: fire event - creature enters battlefield
    }

    /**
     * This is the default casual method to take damage
     * Every damageable target must implement such method
     *
     * @param damage amount of damage taken
     */
    @Override
    public void defaultTakeDamage(int damage) {
        this.damageTaken += damage;
        //TODO: fire event --> creature is dealt damage
    }

    /**
     * Returns damage taken this turn by this creature
     *
     * @return damage taken
     */
    @Override
    public int getDamageTaken() {
        return damageTaken;
    }
}
