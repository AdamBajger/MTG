package cz.mtg.game;

import cz.mtg.abilities.abstracts.Ability;
import cz.mtg.abilities.abstracts.passive.AffectsDestroying;
import cz.mtg.abilities.abstracts.triggered.AbilityTriggered;
import cz.mtg.abilities.abstracts.triggered.WhenExiled;
import cz.mtg.cards.Card;
import cz.mtg.exceptions.*;
import cz.mtg.game.mana.Mana;
import cz.mtg.game.mana.ManaPool;
import cz.mtg.game.mana.ManaSet;
import cz.mtg.game.targets.AttackableTarget;
import cz.mtg.game.zones.Deck;
import cz.mtg.game.zones.Library;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class contains player
 * Player has name, lives and other important stuff
 *
 * Player can participate only in one game at a time. The game, player is currently participating in
 * is stored as an attribute
 */
public class Player implements AttackableTarget {

    private final String name;
    private Game gameAssigned; // this is the game the player participates in
    private int lives;
    private ManaPool manaPool;
    private HashMap<CounterType, Integer> counters;
    private boolean priority;


    // Game zones belonging to player
    private final Library library;
    private final Set<Card> hand;
    private final LinkedList<Card> graveyard;
    private final Set<Card> sideboard;

    public Player(String name) {
        this.name = name;


        this.manaPool = new ManaPool();
        this.counters = new HashMap<>();
        this.priority = false;
        this.library = new Library();
        this.hand = new HashSet<>();
        this.graveyard = new LinkedList<>();
        this.sideboard = new HashSet<>();
    }

    /**
     * TODO: complete implementation
     * TODO: complete JavaDoc
     */
    public void prepareForGame(Game gameAssigned, Deck deck) throws DeckNotAcceptedException {
        this.gameAssigned = gameAssigned;
        // try to initialize library
        try {
            library.initializeLibrary(deck);
        } catch (EmptyDeckException e) {
            throw new DeckNotAcceptedException(e);
        }
        // cache the game format
        GameFormat format = gameAssigned.getFormat();

        // load player's lives
        lives = format.getInitLives();

        // check for number of cards in library
        if(library.getNumberOfCards() < format.getMinCards()) {
            throw new DeckNotAcceptedException("Deck must contain at least "
                    + format.getMinCards() + " cards in " + format + "game format. ");
        }

        switch(format) {
            case CLASSIC:
                break;
            case EDH:
                // TODO: check if deck contains Commander
                // --> some legendary creature
                // TODO: check if all cards match Commander's colors
                // --> you can't have cards of colors that are not in the Commander's mana cost
                // TODO: take out Commander and place it to command zone
                break;
            case TEST:
                break;
            default:
                throw new RuntimeException("Unrecognized game format: " + format);
        }

        // initialize sideboard

        this.sideboard.addAll(deck.getSideboard());

    }

    /**
     * Changes the amount of specific counter type pu on the player
     * @param key the counter type
     * @param amount amount to be changed
     */
    public void changeCounterAmountByValue(CounterType key, int amount) {
        Integer currentAmount = counters.get(key); // if the key was there, it will get a current value, else it gets null
        if(currentAmount != null && amount + currentAmount < 0)
            throw new NegativeNotAllowedException("Negative amount of counters not allowed.");
        // increment current value or add a whole new key with value
        counters.put(key, currentAmount == null ? amount : amount + currentAmount);
    }

    /**
     * Puts exactly one counter of specified type onto this player
     * @param key the specified type of the counter
     */
    public void putCounter(CounterType key) {
        changeCounterAmountByValue(key, 1);
    }

    /**
     * Checks if a player has enough mana for certain spell or ability that ConsumesMana
     * @param spell the mana consuming ability or spell
     * @return true if NotEnoughMana, false otherwise
     */
    public boolean notEnoughMana(ConsumesMana spell) {
        return manaPool.notEnoughMana(spell);
    }

    /**
     * @see ManaPool#spendMana(Mana, ConsumesMana)
     */
    public boolean spendMana(Mana mana, ConsumesMana spell) throws InsufficientManaException {
        return manaPool.spendMana(mana, spell);
    }

    /**
     * @see ManaPool#spendMana(ManaSet, ConsumesMana)
     */
    public void spendMana(ManaSet mana, ConsumesMana spell) throws InsufficientManaException {
        manaPool.spendMana(mana, spell);
    }
    /**
     * @see ManaPool#addMana(Mana)
     */
    public boolean addMana(Mana mana) {
        return manaPool.addMana(mana);
    }

    /**
     * @see ManaPool#addAllMana(ManaSet)
     */
    public void addAllMana(ManaSet manaCol) {
        manaPool.addAllMana(manaCol);
    }

    public Game getGameAssigned() {
        return gameAssigned;
    }
    @Deprecated
    public Set<Card> getHand() {
        return hand;
    }


    @Deprecated
    public LinkedList<Card> getGraveyard() {
        return graveyard;
    }
    @Deprecated
    public Set<Card> getSideboard() {
        return sideboard;
    }

    @Deprecated
    public Library getLibrary() {
        return library;
    }

    public String getName() {
        return name;
    }

    public int getLives() {
        return lives;
    }

    public HashMap<CounterType, Integer> getCounters() {
        return new HashMap<>(counters);
    }

    public boolean hasPriority() {
        return priority;
    }

    /**
     * This method tells you if this targetable entity can be a valid target of a source
     *
     * @param source a source targeting this player
     * @return boolean, where player can be targeted or not
     */
    @Override
    public boolean canBeTargetOfSource(Source source) {
        return false;
    }

    @Override
    public void defaultTakeDamage(int damage) {
        this.lives -= damage;
    }





    /**
     * This method removes a card from current zone based on {@code cardPlacement} attribute
     * call this method every time you move card from somewhere to somewhere.
     *
     * CAUTION:
     * If you call this method and do not move the card, the card will disappear!
     */

    private void removeCardFromPreviousZone(Card c) {
        //SIDEBOARD, LIBRARY, HAND, STACK, BATTLEFIELD, GRAVEYARD, EXILE, COMMAND_ZONE;
        switch(c.getCardPlacement()) {
            case SIDEBOARD:
                // TODO:
                // remove from its owners sideboard
                // I don't thing this case will happen at any point
                throw new RuntimeException("For some fuckin' sake we had to remove card from sideboard!");
            case LIBRARY:
                if(!library.removeCard(c)) {
                    throw new RuntimeException("Tried to move card from library but Card is not in the library.");
                }
                break;
            case HAND:
                if(!hand.remove(c)) {
                    throw new RuntimeException("Tried to move card from hand but Card is not in hand.");
                }
                break;
            case STACK:
                // not required here, cards are removed from stack automatically, or by ability effects
                break;
            case BATTLEFIELD:
                if(!getGameAssigned().getBattlefield().remove(c)) {
                    throw new RuntimeException("Tried to move card from battlefield but Card is not on battlefield.");
                }
                break;
            case GRAVEYARD:
                if(!graveyard.remove(c)) {
                    throw new RuntimeException("Tried to move card from graveyard but Card is not in graveyard.");
                }
                break;
            case EXILE:
                if(!getGameAssigned().getExile().remove(c)) {
                    throw new RuntimeException("Tried to move card from defaultExile but Card is not in defaultExile.");
                }
                break;
            case COMMAND_ZONE:
                if(!getGameAssigned().getCommandZone().remove(c)) {
                    throw new RuntimeException("Tried to move card from command zone but Card is not there.");
                }
                break;
            case INIT:
                // this is the initial placement of cards before the game is created.
                break;
            default:
                // If we got here, then the card was not in a known game zone, or some other error --> throw Runtime
                throw new RuntimeException("Zone previously containing Card not recognized!");
        }
    }

    /**
     * This will clear the card (because every time you move a card, it is cleared),
     * then it will remove it from the previous zone and sets the right place as current placement.
     * @param c card manipulated
     * @param cardPlacement the new placement
     */
    private void assureMoveOfCardToZone(Card c, CardPlacement cardPlacement) {
        c.clear(); // nullify the counters and attributes
        removeCardFromPreviousZone(c); // remove card from previous zone
        c.setCardPlacement(cardPlacement); // keep track of where the card is
    }







    /**
     * @see Library#searchForACard(Card) for more information
     */
    public Card searchLibraryForACard(Card c) {
        return library.searchForACard(c);
    }

    /**
     * @see Library#searchForACard(String) for more information
     */
    public Card searchLibraryForACard(String name) {
        return library.searchForACard(name);
    }

    /**
     * @see Library#shuffle()
     */
    public void shuffleLibrary() {
        library.shuffle();
    }

    /**
     * Returns the top card of the library without removing it from the library.
     * If the library is empty, it returns null
     * @return the top card if present
     * @see Library#lookAtTopCard()
     */
    public Card lookAtTopCardOfLibrary() {
        if(library.getNumberOfCards() > 0) {
            return library.lookAtTopCard();
        } else {
            return null;
        }
    }

    /**
     * returns the top N cards from the library without removing it from the library
     * @param n N = number of cards revealed
     * @return linked list of N cards
     */
    public LinkedList<Card> lookAtTopNCardsOfLibrary(int n) {
        return library.lookAtTopNCards(n);
    }

    public Card lookAtTheBottomCardOfLibrary() {
        return library.lookAtBottomCard();
    }

    public Card lookAtTheBottomNCardsOfLibrary() {
        return lookAtTheBottomNCardsOfLibrary();
    }

    /**
     * Shuffles this card into library
     * -->  that means it places card somewhere into a its owners library (top is one of the fastest)
     *      and then shuffles the Library
     *      Also the cardPlacement attribute is changed to match game zone
     */
    public void shuffleCardIntoLibrary(Card c) {
        putCardOnTopOfLibrary(c); // this is a complete operation
        shuffleLibrary();
    }

    /**
     * puts a card on top of library of this player
     */
    public void putCardOnTopOfLibrary(Card c) {
        assureMoveOfCardToZone(c, CardPlacement.LIBRARY);
        library.putCardOnTop(c); // put the card to the new zone
    }

    /**
     * Puts a card on the bottom of its owners library
     * Bottom = end of the LinkedList library
     * Also the cardPlacement attribute is changed to match game zone
     */
    public void putCardOnBottomOfLibrary(Card c) {
        assureMoveOfCardToZone(c, CardPlacement.LIBRARY);
        library.putCardOnBottom(c); // put the card to the new zone
    }

    /**
     * Exiles a card.
     * Card is placed to EXILE and its attributes are nulled
     * Also the cardPlacement attribute is changed to match game zone
     */
    public void exileCard(Card c) {
        // By default, all cards are exiled face-up, rule 406.3
        assureMoveOfCardToZone(c, CardPlacement.EXILE);
        getGameAssigned().getExile().add(c); // put the card to the new zone
        for(Ability a : c.getAbilities().stream().filter(a -> a instanceof AbilityTriggered).collect(Collectors.toSet())) {
            if(a instanceof WhenExiled) {
                ((AbilityTriggered)a).putEffectOnStack();
            }
        }
    }

    public void destroyCard(Card c) throws IndestructibleException {
        // check if card is not indestructible
        for(Ability ability : c.getAbilities()) {
            if(ability instanceof AffectsDestroying) {
                // call the alternative destroy() method provided by the ability
                ((AffectsDestroying)ability).destroy();
                break; // when there are more abilities that change how a card is destroyed,
                // only the one added last is applied
            }
        }

        c.clear(); // card loses abilities and counters
        removeCardFromPreviousZone(c); // remove the card from previous zone
        graveyard.push(c);
        c.setCardPlacement(CardPlacement.GRAVEYARD);
        System.out.println("Destroyed...");
    }




    @Override
    public String toString() {
        return "{" +
                "name: '" + name + '\'' +
                ", gameAssigned: " + gameAssigned +
                ", lives: " + lives +
                ", priority: " + priority +
                ",\n manaPool: " + manaPool +
                ",\n counters: " + counters +
                ",\n library: " + library +
                ",\n hand: " + hand +
                ",\n graveyard: " + graveyard +
                ",\n sideboard: " + sideboard +
                '}';
    }
}
