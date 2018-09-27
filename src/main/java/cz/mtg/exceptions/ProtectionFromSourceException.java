package cz.mtg.exceptions;


import cz.mtg.game.Source;

/**
 * This exception steps in in cases, where Protection from source is making troubles
 * Mostly in cases of damaging protected creatures with "damage all" spells
 */
public class ProtectionFromSourceException extends Exception {

    public ProtectionFromSourceException(Source source) {
        super("This card is protected from:" + source.getName());
    }
}
