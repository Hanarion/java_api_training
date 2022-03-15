package fr.lernejo.navy_battle.game.ships;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Ship {
    private final AtomicBoolean sunk = new AtomicBoolean(false);
    protected final AtomicInteger size = new AtomicInteger();

    public Ship() {
    }

    public boolean isSunk() {
        return sunk.get();
    }

    public void sink() {
        this.sunk.set(true);
    }

    public int getSize() {
        return this.size.get();
    }
}
