package cz.mtg.game;

import cz.mtg.cards.Card;
import cz.mtg.exceptions.DeckNotAcceptedException;
import cz.mtg.exceptions.EmptyDeckException;
import cz.mtg.exceptions.InsufficientManaException;
import cz.mtg.exceptions.NegativeNotAllowedException;
import cz.mtg.game.mana.Mana;
import cz.mtg.game.mana.ManaPool;
import cz.mtg.game.mana.ManaSet;
import cz.mtg.game.targets.AttackableTarget;
import cz.mtg.game.zones.Library;

import java.util.*;

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

    private Deck deck;

    // Game zones belonging to player
    private Library library;
    private Set<Card> hand;
    private LinkedList<Card> graveyard;
    private Set<Card> sideboard;

    public Player(String name) {
        this.name = name;


        this.manaPool = new ManaPool();
        this.counters = new HashMap<>();
        this.priority = false;
        this.library = new Library();
        this.hand = new HashSet<>();
        this.graveyard = new LinkedList<>();
    }

    /**
     * This will load a deck
     * It just takes a Deck object and tries to add its cards to corresponding places.
     * If the deck has no cards,
     * @param deck deck to be loaded
     * @return false if the deck is not accepted
     */
    public boolean takeDeck(Deck deck) throws DeckNotAcceptedException {
        try {
            this.library.initializeLibrary(deck);
            GameFormat format = gameAssigned.getFormat();
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
                    break;
                case TEST:
                    break;
                default:
                    throw new RuntimeException("Unrecognized game format: " + format);
            }


            // check if the sideboard is not empty
            if(deck.getSideboard().size() > 0) {
                this.sideboard.addAll(deck.getSideboard());
            } else {
                this.sideboard = new HashSet<>();
            }
            return true;
        } catch (EmptyDeckException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * TODO: complete implementation
     * TODO: complete JavaDoc
     */
    public void prepareForGame(Game gameAssigned) {
        this.gameAssigned = gameAssigned;
        GameFormat format = gameAssigned.getFormat();
        lives = format.getInitLives();
        switch(format) {
            case CLASSIC:
                break;
            case EDH:
                // TODO: take out Commander and place it to command zone
                break;
            case TEST:
                break;
            default:
                throw new RuntimeException("Unrecognized game format: " + format);
        }
    }

    public void changeCounterAmountByValue(CounterType key, int amount) {
        Integer currentAmount = counters.get(key); // if the key was there, it will get a current value, else it gets null
        if(currentAmount != null && amount + currentAmount < 0)
            throw new NegativeNotAllowedException("Negative amount of counters not allowed.");
        // increment current value or add a whole new key with value
        counters.put(key, currentAmount == null ? amount : amount + currentAmount);
    }

    public void putCounter(CounterType key) {
        changeCounterAmountByValue(key, 1);
    }

    public boolean notEnoughMana(ConsumesMana spell) {
        return manaPool.notEnoughMana(spell);
    }

    public boolean spendMana(Mana mana, ConsumesMana spell) throws InsufficientManaException {
        return manaPool.spendMana(mana, spell);
    }

    public void spendMana(ManaSet mana, ConsumesMana spell) throws InsufficientManaException {
        manaPool.spendMana(mana, spell);
    }

    public boolean addMana(Mana mana) {
        return manaPool.addMana(mana);
    }

    /**
     * Add all mana objects in a mana collection
     * @param manaCol given mana collection
     */
    public void addAllMana(ManaSet manaCol) {
        manaPool.addAllMana(manaCol);
    }

    public Game getGameAssigned() {
        return gameAssigned;
    }

    public Set<Card> getHand() {
        return hand;
    }

    public LinkedList<Card> getGraveyard() {
        return graveyard;
    }

    public Set<Card> getSideboard() {
        return sideboard;
    }

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
        return counters;
    }

    public boolean hasPriority() {
        return priority;
    }

    @Override
    public void defaultTakeDamage(int damage) {
        this.lives -= damage;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", gameAssigned=" + gameAssigned +
                ", lives=" + lives +
                ", priority=" + priority +
                ",\n manaPool=" + manaPool +
                ",\n counters=" + counters +
                ",\n deck=" + deck +
                ",\n library=" + library +
                ",\n hand=" + hand +
                ",\n graveyard=" + graveyard +
                ",\n sideboard=" + sideboard +
                '}';
    }
}
