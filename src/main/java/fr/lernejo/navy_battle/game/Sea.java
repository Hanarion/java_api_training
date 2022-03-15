package fr.lernejo.navy_battle.game;

import java.util.Vector;

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

}
