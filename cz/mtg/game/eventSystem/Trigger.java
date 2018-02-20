/*
 * Copyright (c) 2018. This piece of art or work is owned by me and I do not allow you to do with it anything that would violate the idea with which I created it. If you are unsure if what you are going to do is okay, contact me first on kyrilcouda@gmail.com
 */

package cz.mtg.game.eventSystem;

import cz.mtg.game.eventSystem.types.EventType;

/**
 * This interface represents a Trigger that can be stored in Trigger pool, waiting for Trigger event.
 *
 */
public abstract class Trigger {
    private EventType eventType;

    public Trigger(EventType eventType) {
        this.eventType = eventType;
    }
}
