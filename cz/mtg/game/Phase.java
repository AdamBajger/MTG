package cz.mtg.game;

/**
 * Enumerates particular phases of a turn
 * For more info see corresponding file also available in directory MTGResources
 */
public enum Phase {
    UNTAP, UPKEEP, DRAW,
    MAIN_PRECOMBAT,
    BEGIN_COMBAT, DECLARE_ATTACKERS, DECLARE_BLOCKERS, COMBAT_DAMAGE_FIRST, COMBAT_DAMAGE, END_COMBAT,
    MAIN_POSTCOMBAT,
    END, CLEANUP



}
