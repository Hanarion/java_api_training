package fr.lernejo.navy_battle.handlers;

import fr.lernejo.navy_battle.NavyBattleServer;

public abstract class Handler {
    private final NavyBattleServer battleServer;

    public Handler(NavyBattleServer battleServer) {
        this.battleServer = battleServer;
    }

    public NavyBattleServer getBattleServer() {
        return battleServer;
    }
}
