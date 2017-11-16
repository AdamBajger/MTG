package cz.mtg;

import cz.mtg.exceptions.InsufficientManaException;
import cz.mtg.exceptions.InvalidActionException;
import cz.mtg.game.Color;
import cz.mtg.game.Player;
import cz.mtg.game.Stack;
import cz.mtg.testCards.BlightsteelColossus;

public class Main {

    public static void main(String[] args) {
        Player adam = new Player();
        Stack stack = new Stack();

        BlightsteelColossus blightsteelColossus = new BlightsteelColossus(adam);


        try {
            blightsteelColossus.cast(stack);
        } catch (InsufficientManaException | InvalidActionException e) {
            e.printStackTrace();
        }

        adam.getManaPool().addManaOfColor(Color.COLORLESS, 12);

        try {
            blightsteelColossus.cast(stack);
        } catch (InsufficientManaException | InvalidActionException e) {
            e.printStackTrace();
        }

        System.out.println(stack.toString());
        stack.resolveNext();
        System.out.println(stack.toString());

    }
}
