package cz.mtg;

import cz.mtg.abilities.Ability;
import cz.mtg.abilities.TapForManaAbility;
import cz.mtg.cards.Card;
import cz.mtg.exceptions.AlreadyTappedOrUntappedException;
import cz.mtg.exceptions.DeckNotAcceptedException;
import cz.mtg.exceptions.InsufficientManaException;
import cz.mtg.exceptions.InvalidActionException;
import cz.mtg.game.*;
import cz.mtg.game.mana.BasicMana;
import cz.mtg.game.mana.Mana;
import cz.mtg.game.mana.ManaSet;
import cz.mtg.testCards.BlightsteelColossus;
import cz.mtg.testCards.Island;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        Player adam = new Player("Adam");
        Card island = new Island(adam);
        TapForManaAbility tapForMana = new TapForManaAbility(island, new ManaSet(), new ManaSet(new HashSet<>(Arrays.asList(new BasicMana(island, Color.RED), new BasicMana(island, Color.BLUE)))));


        try {
            tapForMana.tapForMana();
            island.untap();
            tapForMana.tapForMana();
            island.untap();
            tapForMana.tapForMana();
            island.untap();
            tapForMana.tapForMana();
            island.untap();
            tapForMana.tapForMana();
        } catch (AlreadyTappedOrUntappedException | InsufficientManaException e) {
            e.printStackTrace();
        }
        // print info about player adter 1st usage of tapMana
        System.out.println(adam.toString());

        //---------------------------------------
        TapForManaAbility expensiveTapForMana = new TapForManaAbility(island,
                new ManaSet(new HashSet<>(Arrays.asList( new BasicMana(6, island, Color.RED), new BasicMana(5, island, Color.BLUE)))),
                new ManaSet(new HashSet<>(Collections.singletonList(new BasicMana(4, island, Color.GREEN)))));

        try {
            island.untap();
            expensiveTapForMana.tapForMana();
        } catch (AlreadyTappedOrUntappedException | InsufficientManaException e) {
            e.printStackTrace();
        }

        System.out.println(adam.toString());


    }
}
