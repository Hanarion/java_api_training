package fr.lernejo.navy_battle.handlers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.NavyBattleServer;
import fr.lernejo.navy_battle.game.Player;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class StartGameHandler extends Handler implements HttpHandler {
    public StartGameHandler(NavyBattleServer battleServer) {
        super(battleServer);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if (!httpExchange.getRequestMethod().equals("POST")) {
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
            return;
        }
        String json = new String(httpExchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        Player player = new Player().getFromJson(json);
        this.getBattleServer().setOponnent(player);
        String response = this.getBattleServer().getPlayer().toJson();
        sendResponse(httpExchange, response);
    }

    public void sendResponse(HttpExchange httpExchange, String response) throws IOException {
        try {

            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_ACCEPTED, response.getBytes().length);
            httpExchange.getResponseBody().write(response.getBytes());
            httpExchange.close();
        } catch (IOException e) {
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }
    }
}
