package tests;

import cz.mtg.abilities.IndestructibleAbility;
import cz.mtg.cards.Card;
import cz.mtg.cards.castable.creature.AbstractCreatureCard;
import cz.mtg.exceptions.IndestructibleException;
import cz.mtg.game.Game;
import cz.mtg.game.GameFormat;
import cz.mtg.game.Player;
import cz.mtg.game.mana.ManaSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndestructibleAbilityTest {
    private Player tester = new Player("tester");
    private Game testGame = new Game(GameFormat.CLASSIC);

    private Card indestructibleCard = new AbstractCreatureCard("testCard", tester, new ManaSet(), 5, 5) {

    };




    @Test
    void testDestroy() {
        //tester.prepareForGame(testGame);
        testGame.getExile().add(indestructibleCard);
        indestructibleCard.addAbility(new IndestructibleAbility(indestructibleCard));
        assertThrows(IndestructibleException.class, () -> indestructibleCard.destroy());
    }


}