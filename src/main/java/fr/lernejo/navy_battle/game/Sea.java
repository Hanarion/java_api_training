package fr.lernejo.navy_battle.game;

import fr.lernejo.navy_battle.game.ships.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class Sea {
    private final int width;
    private final int height;
    private final Vector<Vector<Case>> cells = new Vector<>();

    public Sea(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Sea() {
        this.width = 10;
        this.height = 10;
    }

    private void populateSeaWithShips(List<Ship> ships) {
        Random rand = new Random();
        List<Ship> toAdd = new ArrayList<>(ships);
        while (!toAdd.isEmpty()) {
            Ship ship = toAdd.get(toAdd.size() - 1);
            Case cell;
            do {
                int x = rand.nextInt(this.width);
                int y = rand.nextInt(this.height);
                cell = this.cells.get(x).get(y);
            } while (cell.hasShip());
            cell.setShip(ship);
            toAdd.remove(ship);
        }
    }

    public void populateSea(List<Ship> ships) {
        for (int i = 0; i < this.width; i++) {
            Vector<Case> vec = new Vector<>();
            this.cells.add(i, vec);
            for (int j = 0; j < this.height; j++) {
                vec.add(j, new Case(i, j));
            }
        }
        this.populateSeaWithShips(ships);
    }

    public Case getCase(String cell) {
        String processed = cell.toLowerCase().strip();
        String left = processed.replaceAll("[0-9]", "");
        String right = processed.replaceAll("[a-z]", "");
        AtomicInteger leftNum = new AtomicInteger();
        AtomicInteger leftSize = new AtomicInteger(0);

        left.chars().forEach((elem) -> {
            leftNum.addAndGet((int) Math.pow(26, leftSize.get()) * (elem - 'a'));
        });

        int rightNum = Integer.getInteger(right);
        return cells.get(leftNum.get()).get(rightNum);
    }

    public HitState hitCase(String cell) {
        return hitCase(getCase(cell));
    }

    public HitState hitCase(Case cell) {

        cell.setHit(true);

        if (cell.hasShip()) {
            if (cell.getShip().hit())
                return HitState.STATE_SUNK;
            else
                return HitState.STATE_HIT;
        } else
            return HitState.STATE_MISS;
    }

}
