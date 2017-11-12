package cz.mtg.game;

/**
 * This class describes all allowed types of cards.
 * It will probably never reach fullness, as there is really lot of types in MTG
 * If you encounter a type that is not listed bellow, add it
 *  IMPORTANT NOTE:
 *      The order of these types is NOT RANDOM
 *      Order describes which types have greater importance
 *      If a type A is sorted after type B, then in one card they will also maintain the same order
 *      If two types have SAME IMPORTANCE, sort them ALPHABETICALLY.
 */
public enum Type {
    LEGENDARY, TRIBAL, SNOW, BASIC,
    ARTIFACT, ENCHANTMENT, CREATURE, LAND, INSTANT, SORCERY, PLANESWALKER,

    // HERE COME THE MINOR TYPES ---> races, species, fellowships...

    ANGEL,
    BEAST,
    BIRD,
    ELDRAZI,
    ELF,
    ELK,
    GIANT,
    GOBLIN,
    HUMAN,
    INSECT,
    KOR,
    MYR,
    SPIDER,
    SPHINX,
    SPIRIT,
    TROLL,
    VAMPIRE,
    WURM,

    // HERE COME MORE MINOR TYPES ---> jobs, specializations, roles

    CLERIC,
    CONSTRUCT,
    MAGE,
    ROGUE,
    SHAMAN,
    WALL,
    WARRIOR,

    // SPECIAL TYPES --- these are always last

    ALLY,


}
