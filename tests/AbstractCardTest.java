package tests;

import cz.mtg.abilities.BasicTapForManaAbility;
import cz.mtg.abilities.DoubleStrike;
import cz.mtg.abilities.IndestructibleAbility;
import cz.mtg.abilities.abstracts.Ability;
import cz.mtg.cards.Card;
import cz.mtg.exceptions.*;
import cz.mtg.game.*;
import cz.mtg.game.mana.BasicMana;
import cz.mtg.game.mana.ManaSet;
import cz.mtg.game.zones.Deck;
import cz.mtg.testCards.BlightsteelColossus;
import cz.mtg.testCards.Glimmerpost;
import cz.mtg.testCards.Island;
import cz.mtg.testCards.SporecapSpider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractCardTest {

    private static Player tester = new Player("tester");
    private static Game testingGame = new Game(GameFormat.TEST);
    private static Card testcard = new BlightsteelColossus(tester);
    private static Card testGlimmerpost = new Glimmerpost(tester);
    private static Card testIsland1 = new Island(tester);
    private static Card testIsland2 = new Island(tester);
    private static Card testIsland3 = new Island(tester);
    private static Card testIsland4 = new Island(tester);
    private static Card testSideCard = new Glimmerpost(tester);
    private static Deck testDeck = new Deck(
            new HashSet<>(Arrays.asList(
                    testcard,
                    testGlimmerpost,
                    testIsland1,
                    testIsland2,
                    testIsland3,
                    testIsland4)
            ),
            Collections.singleton(testSideCard));
    private static List<Ability> abilities = testcard.getAbilities();

    @BeforeAll
    static void initialize() {
        assertSame(testcard.getCardPlacement(), CardPlacement.INIT);
        try {
            tester.prepareForGame(testingGame, testDeck);
        } catch (DeckNotAcceptedException e) {
            fail("Deck was not accepted.", e);
        }

    }

    @Test
    void getName() {
        assertSame("tester", tester.getName());
    }

    @Test
    void getOwner() {
        assertSame(tester, testcard.getOwner());
    }

    @Test
    void getController() {
        assertSame(tester, testcard.getController());
    }

    @Test
    void setController() {
        Player dummy = new Player("dummy");
        testcard.setController(dummy);
        assertSame(dummy, testcard.getController());
        testcard.setController(tester);
    }

    @Test
    void tapping() {
        // card should be initially tapped
        assertFalse(testcard.isTapped());
        assertThrows(AlreadyTappedOrUntappedException.class, () -> testcard.untap());
        try {
            testcard.tap();
        } catch (AlreadyTappedOrUntappedException e) {
            fail(e);
        }
        assertTrue(testcard.isTapped());
        assertThrows(AlreadyTappedOrUntappedException.class, () -> testcard.tap());
        try {
            testcard.untap();
        } catch (AlreadyTappedOrUntappedException e) {
            fail(e);
        }
        assertFalse(testcard.isTapped());
    }



    @Test
    void facing() {
        assertFalse(testcard.isFacedDown());
        testcard.flip();
        assertTrue(testcard.isFacedDown());
        testcard.flip();
        assertFalse(testcard.isFacedDown());
    }


    @Test
    void getAbilities() {

        assertThrows(Exception.class, () -> abilities.add(new BasicTapForManaAbility(testcard, new ManaSet(), new ManaSet())));
    }

    @Test
    void addAbility() {

        Ability ability = new DoubleStrike(testcard);
        assertFalse(abilities.contains(ability));
        testcard.addAbility(ability);
        assertTrue(abilities.contains(ability));


    }

    @Test
    void removeAbility() {
        Ability ability = new IndestructibleAbility(testcard);
        assertTrue(abilities.contains(ability));
        assertTrue(testcard.removeAbility(ability));
        assertFalse(abilities.contains(ability));
    }

    @Test
    void getCardPlacement() {
        // when a card is included into a deck, it starts in library
        assertSame(CardPlacement.LIBRARY, testcard.getCardPlacement());

    }

    @Test
    void setCardPlacement() {
        tester.exileCard(testcard);
        assertSame(CardPlacement.EXILE, testcard.getCardPlacement());
        tester.putCardOnTopOfLibrary(testcard);
        assertSame(CardPlacement.LIBRARY, testcard.getCardPlacement());

    }

    @Test
    void defaultPutCounters() {
        assertSame(0, testcard.getCounterAmount(CounterType.P_T_COUNTER_POSITIVE));
        testcard.putCounters(CounterType.P_T_COUNTER_POSITIVE, 2);
        testcard.putCounters(CounterType.P_T_COUNTER_POSITIVE, 3);
        assertSame(5, testcard.getCounterAmount(CounterType.P_T_COUNTER_POSITIVE));
    }

    @Test
    void defaultRemoveCounters() {
        //System.err.println("+1/+1 counters: " + testcard.getCounterAmount(CounterType.P_T_COUNTER_POSITIVE));
        assertThrows(InvalidActionException.class,
                () -> testcard.removeCounters(CounterType.P_T_COUNTER_POSITIVE, 6));
        testcard.putCounters(CounterType.P_T_COUNTER_POSITIVE, 1);
        assertThrows(RestrictedCounterAmountException.class,
                () -> testcard.removeCounters(CounterType.P_T_COUNTER_POSITIVE, 6));
        try{
            testcard.removeCounters(CounterType.P_T_COUNTER_POSITIVE, 1);
        } catch (Throwable t) {
            fail(t);
        }

        int currentCounters = testcard.getCounterAmount(CounterType.P_T_COUNTER_POSITIVE);
        if(currentCounters != 0) {
            try {
                testcard.removeCounters(CounterType.P_T_COUNTER_POSITIVE, currentCounters);
            } catch (RestrictedCounterAmountException e) {
                fail(e);
            }
        }

        assertSame(0, testcard.getCounterAmount(CounterType.P_T_COUNTER_POSITIVE));
    }

    @Test
    void getCounterAmount() {
        testcard.putCounters(CounterType.P_T_COUNTER_NEGATIVE, 1);
        testcard.putCounters(CounterType.P_T_COUNTER_NEGATIVE, 1);
        assertSame(2, testcard.getCounterAmount(CounterType.P_T_COUNTER_NEGATIVE));
        try {
            testcard.removeCounters(CounterType.P_T_COUNTER_NEGATIVE, 1);
            testcard.removeCounters(CounterType.P_T_COUNTER_NEGATIVE, 1);
        } catch(RestrictedCounterAmountException e) {
            fail(e);
        }
        assertSame(0, testcard.getCounterAmount(CounterType.P_T_COUNTER_NEGATIVE));
    }

    @Test
    void clear() {
        SporecapSpider tc2 = new SporecapSpider(tester);
        tester.addMana(new BasicMana(1, testIsland1, Color.GREEN));
        tester.addMana(new BasicMana(2, testIsland2, Color.COLORLESS));

        try {
            tc2.cast();
            tc2.resolve();
        } catch (InsufficientManaException e) {
            fail(e);
        }

        tc2.putCounters(CounterType.QUEST, 2);
        tc2.putCounters(CounterType.LEVEL, 2);
        tc2.putCounters(CounterType.CHARGE, 1);
        tc2.putCounters(CounterType.CHARGE, 1);
        tc2.putCounters(CounterType.TIME, 2);

        assertSame(2, tc2.getCounterAmount(CounterType.QUEST));
        assertSame(2, tc2.getCounterAmount(CounterType.LEVEL));
        assertSame(2, tc2.getCounterAmount(CounterType.CHARGE));
        assertSame(2, tc2.getCounterAmount(CounterType.TIME));
        assertSame(0, tc2.getCounterAmount(CounterType.SPORE));
        try {
            tc2.tap();
        } catch (AlreadyTappedOrUntappedException e) {
            fail(e);
        }

        tester.exileCard(tc2);
        assertSame(0, tc2.getCounterAmount(CounterType.QUEST));
        assertSame(0, tc2.getCounterAmount(CounterType.LEVEL));
        assertSame(0, tc2.getCounterAmount(CounterType.CHARGE));
        assertSame(0, tc2.getCounterAmount(CounterType.TIME));
        assertFalse(tc2.isFacedDown());
        assertFalse(tc2.isTapped());

    }

    @Test
    void shuffleIntoLibrary() {
        Card testShuffleCard = new SporecapSpider(tester);
        //first check that the card is not in library
        assertNull(tester.searchLibraryForACard(testShuffleCard));

        // shuffle the card into library
        tester.shuffleCardIntoLibrary(testShuffleCard);

        // assert that the card ended up in the library
        Card foundCard;
        assertNotNull(foundCard = tester.searchLibraryForACard(testShuffleCard));
        assertSame(foundCard, testShuffleCard);
    }

    @Test
    void putOnTopOfLibrary() {
        Card putOnTopCard = new Island(tester);
        tester.putCardOnTopOfLibrary(putOnTopCard);
        assertSame(tester.lookAtTopCardOfLibrary(), putOnTopCard);
    }

    @Test
    void putOnBottomOfLibrary() {
        Card putOnBottomCard = new Island(tester);
        tester.putCardOnBottomOfLibrary(putOnBottomCard);
        assertSame(tester.lookAtTheBottomCardOfLibrary(), putOnBottomCard);
    }

    @Test
    void exile() {
        Card cardToExile = new BlightsteelColossus(tester);
        tester.exileCard(cardToExile);
        //TODO
    }

    @Test
    void defaultDestroy() {
        //TODO
    }

}