package fr.lernejo.navy_battle.game;

import fr.lernejo.navy_battle.game.ships.Ship;

import java.util.concurrent.atomic.AtomicReference;

public class Case {
    private final AtomicReference<Ship> ship = new AtomicReference<>();

    public void setShip(Ship ship) {
        this.ship.set(ship);
    }

    public Ship getShip() {
        return this.ship.get();
    }

    public boolean hasShip() {
        return this.ship.get() != null;
    }
}
