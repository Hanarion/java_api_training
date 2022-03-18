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
    public void handle(HttpExchange httpExchange) {
        if (!httpExchange.getRequestMethod().equals("POST")) {
            try {
                String response = "Not found";
//                httpExchange.getResponseBody().write(response.getBytes());
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return;

        }
        ObjectMapper mapper = new ObjectMapper();
        Player player;
        try {
            String input = new String(httpExchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);

            player = mapper.readValue(input, Player.class);

            this.getBattleServer().setOponnent(player);
            this.getBattleServer().startNewGame();
            String response = mapper.writeValueAsString(this.getBattleServer().getPlayer());

            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_ACCEPTED, response.getBytes().length);
            httpExchange.getResponseBody().write(response.getBytes());
        } catch (Exception e) {
//            e.printStackTrace();
            try {
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return;
        }

    }
}
