package fr.lernejo.navy_battle.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.NavyBattleServer;
import fr.lernejo.navy_battle.game.Player;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class FireHandler extends Handler implements HttpHandler {
    public FireHandler(NavyBattleServer battleServer) {
        super(battleServer);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if (!httpExchange.getRequestMethod().equals("GET")) {
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
            return;
        }
        try {
            String json = new String(httpExchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            String response = "{\"consequence\":\"sunk\",\"shipLeft\":true}";
            httpExchange.getResponseHeaders().set("Content-Type", "application/json;");
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_ACCEPTED, response.getBytes().length);
            httpExchange.getResponseBody().write(response.getBytes());
        } catch (IOException e) {
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }

    }
}
