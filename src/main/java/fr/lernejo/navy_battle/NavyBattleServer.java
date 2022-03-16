package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.game.Player;
import fr.lernejo.navy_battle.handlers.PingHandler;
import fr.lernejo.navy_battle.handlers.StartGameHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class NavyBattleServer {
    private final String ip;
    private final int port;
    private final Map<String, HttpHandler> handlerMap = new HashMap<>();
    private final Player player;
    private final AtomicReference<Player> oponnent = new AtomicReference<>();
    private final AtomicReference<HttpServer> httpServer = new AtomicReference<>();

    public NavyBattleServer(String ip, int port, Player player) {
        this.ip = ip;
        this.port = port;
        this.player = player;

        this.addHandler("/ping", new PingHandler(this));
        this.addHandler("/api/game/start", new StartGameHandler(this));
    }

    public void addHandler(String endpoint, HttpHandler handler) {
        this.handlerMap.put(endpoint, handler);
    }

    public void startHttpServer() {
        InetSocketAddress addr = new InetSocketAddress(this.ip, this.port);

        try {
            HttpServer server = HttpServer.create(addr, 0);

            ExecutorService executor = Executors.newFixedThreadPool(1);
            server.setExecutor(executor);
            for (Map.Entry<String, HttpHandler> handler : handlerMap.entrySet()) {
                server.createContext(handler.getKey(), handler.getValue());
            }
            this.httpServer.set(server);
            server.start();
            System.out.println("Server available at http://" + addr);
        } catch (IOException e) {
            System.out.println("Impossible de créer un serveur, le port est disponible ??");
            return;
        }

    }

    public void stopHttpServer() {
        this.httpServer.get().stop(0);
    }

    public Player getOponnent() {
        return oponnent.get();
    }

    public void setOponnent(Player p) {
        System.out.println("New opponent as arrived :");
        System.out.println(p);
        oponnent.set(p);
    }

    public Player getPlayer() {
        return player;
    }
}
