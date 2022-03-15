package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.handlers.PingHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NavyBattleServer {
    private final String ip;
    private final int port;
    private final Map<String, HttpHandler> handlerMap = new HashMap<>();

    public NavyBattleServer(String ip, int port) {
        this.ip = ip;
        this.port = port;

        this.addHandler("/ping", new PingHandler());
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

            server.start();
        } catch (IOException e) {
            System.out.println("Impossible de créer un serveur, le port est disponible ??");
            return;
        }

    }
}
