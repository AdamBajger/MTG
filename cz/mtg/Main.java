package cz.mtg;

import cz.mtg.cards.Card;
import cz.mtg.exceptions.DeckNotAcceptedException;
import cz.mtg.exceptions.InsufficientManaException;
import cz.mtg.exceptions.InvalidActionException;
import cz.mtg.game.*;
import cz.mtg.testCards.BlightsteelColossus;
import cz.mtg.testCards.Plains;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {

        Game mainGame = new Game(GameFormat.TEST);

        Player adam = new Player("Adam");
        adam.prepareForGame(mainGame);
        try {
            adam.takeDeck(new Deck(new HashSet<Card>(Arrays.asList(new Plains(adam))), Collections.emptySet()));
        } catch (DeckNotAcceptedException e) {
            e.printStackTrace();
        }

        Deck newDeck = new Deck( new HashSet<>(), new HashSet<>());

        BlightsteelColossus blightsteelColossus = new BlightsteelColossus(adam);


        try {
            blightsteelColossus.cast();
        } catch (InsufficientManaException | InvalidActionException e) {
            e.printStackTrace();
        }
        System.out.println(mainGame.getSpellStack().toString());
        adam.getManaPool().addManaOfColor(Color.COLORLESS, 12);

        try {
            blightsteelColossus.cast();
        } catch (InsufficientManaException | InvalidActionException e) {
            e.printStackTrace();
        }
        // print info
        System.out.println(mainGame.getSpellStack().toString());
        //System.out.println(mainGame.getBattlefield().toString());
        // cast card
        if(mainGame.getSpellStack().isEmpty()) {
            System.out.println("Spell stack is empty. ");
        } else {
            mainGame.getSpellStack().resolveNext();
        }
        // print new info
        System.out.println(mainGame.getSpellStack().toString());
        //System.out.println(mainGame.getBattlefield().toString());

    }
}
