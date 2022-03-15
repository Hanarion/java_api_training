package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.handlers.PingHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Merci de préciser le port");
            return;
        }
        try {
            int port;

            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Ce n'est pas un port valide");
                return;
            }

            if (port > 0 && port < 65535) {
                InetSocketAddress addr = new InetSocketAddress("0.0.0.0", port);

                HttpServer server = HttpServer.create(addr, 0);

                ExecutorService executor = Executors.newFixedThreadPool(1);
                server.setExecutor(executor);

                HttpHandler handler = new PingHandler();

                server.createContext("/ping", handler);

                server.start();
            } else {
                System.out.println("Merci d'indiquer un port valide");
                return;
            }
        } catch (IOException e) {
            System.out.println("Impossible de créer un serveur, le port est disponible ??");
            return;
        }
    }
}
