package fr.lernejo.navy_battle.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.HttpURLConnection;

public class PingHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "OK";
        httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
        httpExchange.getResponseBody().write(response.getBytes());
    }
}
