package cz.mtg.cards.castable.creature;

import cz.mtg.cards.castable.CastableCard;
import cz.mtg.exceptions.IndestructibleException;
import cz.mtg.exceptions.NegativeNotAllowedException;
import cz.mtg.game.ManaCollection;
import cz.mtg.game.Player;
import cz.mtg.game.targets.DamageableTarget;

/**
 * This class is here to allow PRIVATE attributes for creature
 * It will allow us to control how attributes (like defaultPower, defaultToughness, summonningSickness, etc.) are modified
 *
 * ----------------------------
 *  TODO:
 *      define appropriate attributes HTV
 *      define appropriate getters
 *      define appropriate setters
 */
public abstract class CreatureCard extends CastableCard implements Creature {
    private boolean summoningSickness;
    private final int defaultPower;
    private int powerBonus;
    private final int defaultToughness;
    private int toughnesBonus;
    private int damage;

    /**
     * Creates a CreatureCard with initialized name, mana cost, and P/T
     * @param name name
     * @param manaCost mana cost
     * @param power defaultPower
     * @param toughness defaultToughness
     */
    public CreatureCard(String name, Player owner, ManaCollection manaCost, int power, int toughness) {
        super(name, owner, manaCost);
        this.defaultPower = power;
        this.defaultToughness = toughness;
    }

    @Override
    public boolean defaultHasSummoningSickness() {
        return summoningSickness;
    }

    @Override
    public int getPower() {
        return defaultPower + powerBonus;
    }

    @Override
    public int getToughness() {
        return defaultToughness + toughnesBonus;
    }

    @Override
    public boolean defaultCast() {

        return false;
    }

    @Override
    public void defaultDealDamage(DamageableTarget damageableTarget) throws NegativeNotAllowedException {
        damageableTarget.takeDamage(this.getPower());
    }


    @Override
    public void defaultTakeDamage(int damage) {
        this.damage += damage;
        // fire event creature is dealt damage
        if(this.damage >= defaultToughness) {
            try {
                destroy();

            } catch(IndestructibleException e) {
                // print exception for now
                e.printStackTrace();
            }
            // fire event creature is destroyed
        }
    }

    /**
     * Appends creature info to the given StruingBuilder
     * P/T, sum. sickness, damage taken,  etc...
     * @param sb given StruingBuilder
     */
    protected void appendCreatureInfo(StringBuilder sb) {
        sb.append(", ");

        // append defaultPower +- powerBonus
        sb.append(defaultPower);
        if(powerBonus > 0) {
            sb.append("+").append(powerBonus);
        } else if(powerBonus < 0) {
            sb.append(powerBonus);
        }

        // append divisor, that intersects defaultPower and defaultToughness
        sb.append("/");

        // append defaultToughness +- toughnessBonus
        sb.append(defaultToughness);
        if(toughnesBonus > 0) {
            sb.append("+").append(toughnesBonus);
        } else if(toughnesBonus < 0) {
            sb.append(toughnesBonus);
        }

        sb.append(", ").append(damage).append(" damage taken");

        if(hasSummoningSickness()) {
            sb.append(", has sickness");
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendBasicInfo(sb);
        appendManaCostInfo(sb);
        appendStateInfo(sb);

        return sb.toString();
    }
}
