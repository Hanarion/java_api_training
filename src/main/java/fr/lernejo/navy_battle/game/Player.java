package fr.lernejo.navy_battle.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class Player {
    private final UUID id;
    private final String url;
    private final String message;

    public Player() {
        this.id = null;
        this.url = null;
        this.message = null;
    }

    public Player(UUID id, String url, String message) {
        this.id = id;
        this.url = url;
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getMessage() {
        return message;
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String response;
        try {
            response = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public Player getFromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Player player;
        player = mapper.readValue(json, Player.class);
        return player;
    }

    public Player getFromUrl(String str_url, Player other_player) {
        Player player;
        URL url = null;
        try {
            url = new URL(str_url + "/api/game/start");
        } catch (MalformedURLException e) {
            System.out.println("Merci de préciser une URL valide");
            return null;
        }
        HttpURLConnection con = null;
        try {

            con = (HttpURLConnection) url.openConnection();


            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");

            ObjectMapper mapper = new ObjectMapper();
            String toSend = mapper.writeValueAsString(other_player);
            try (OutputStream output = con.getOutputStream()) {
                output.write(toSend.getBytes());
            }

            InputStream response = con.getInputStream();
            player = mapper.readValue(response, Player.class);
            con.disconnect();
            return player;

        } catch (IOException e) {
            System.out.println("Serveur impossible à joindre");
            return null;
        }

    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
