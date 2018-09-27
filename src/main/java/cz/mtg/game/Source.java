/*
 * Copyright (c) 2018. This piece of art or work is owned by me and I do not allow you to do with it anything that would violate the idea with which I created it. If you are unsure if what you are going to do is okay, contact me first on kyrilcouda@gmail.com
 */

package cz.mtg.game;

import cz.mtg.game.Player;

/**
 * This class is a container unifying Ability and Card as a source.
 * Thanks to this class we are able to talk about Ability source and Card source.
 * It allows us to distinguish whether a source is Card or an Ability only by asking if
 * Source instanceof Ability/Card
 */
public interface Source {

    /**
     * This method allows you to retrieve a controlling player of the card or ability
     * @return controlling player of this source, obviously
     */
    Player getController();

    String getName();
}
