package fr.lernejo.navy_battle.game;

import fr.lernejo.navy_battle.game.ships.Ship;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Case {

    private final int x;
    private final int y;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private final AtomicReference<Ship> ship = new AtomicReference<>();

    private final AtomicBoolean hit = new AtomicBoolean(false);

    public void setShip(Ship ship) {
        this.ship.set(ship);
    }

    public Ship getShip() {
        return this.ship.get();
    }

    public boolean hasShip() {
        return this.ship.get() != null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isHit() {
        return hit.get();
    }

    public void setHit(boolean hit)
    {
        this.hit.set(hit);
    }
}
