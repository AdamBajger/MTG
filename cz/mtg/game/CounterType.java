package cz.mtg.game;

/**
 * This enum contains all implemented counter types available
 * there is not too much counters to not implement it like this
 * It will also minimize errors popping from using Strings
 */
public enum CounterType {
    CHARGE,
    LEVEL,
    LOYALTY,
    P_T_COUNTER_POSITIVE, // +1/+1 counters
    P_T_COUNTER_NEGATIVE, // -1/-1 counters
    POISON,
    QUEST,
    SPORE,
    TIME


}
