package fr.lernejo.navy_battle.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.NavyBattleServer;

import java.io.IOException;
import java.net.HttpURLConnection;

public class PingHandler extends Handler implements HttpHandler {

    public PingHandler(NavyBattleServer battleServer) {
        super(battleServer);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
//        if (!httpExchange.getRequestMethod().equals("GET"))
//            return;
        String response = "OK";
        httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
        httpExchange.getResponseBody().write(response.getBytes());
    }
}
