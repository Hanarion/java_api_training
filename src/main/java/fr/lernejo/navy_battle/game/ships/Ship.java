package fr.lernejo.navy_battle.game.ships;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Ship {
    private final AtomicInteger nb_hit = new AtomicInteger(0);
    protected final AtomicInteger size = new AtomicInteger();

    public Ship() {
    }

    public boolean isSunk() {
        return nb_hit.get() >= size.get();
    }

    public boolean hit() {
        nb_hit.incrementAndGet();
        return isSunk();
    }

    public int getSize() {
        return this.size.get();
    }


}
