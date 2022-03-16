package fr.lernejo.navy_battle.handlers;

import fr.lernejo.navy_battle.Launcher;
import org.junit.jupiter.api.Assertions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class StartTest {
    @org.junit.jupiter.api.Test
    void startWithGet() throws IOException {
        String[] args = {"18596"};
        Launcher.main(args);

        URL url = new URL("http://localhost:18596/api/game/start");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int status = con.getResponseCode();
        Assertions.assertEquals(status, 404, "Le statut HTTP n'est pas valide");
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//
//        StringBuilder content = new StringBuilder();
//        String inputLine;
//        while ((inputLine = in.readLine()) != null) {
//            content.append(inputLine);
//        }
//        in.close();
//
//        Assertions.assertEquals(content.toString(), "Not found", "Le message n'est pas valide");
    }

    @org.junit.jupiter.api.Test
    void startWithBadPost() throws IOException {
        String[] args = {"18597"};
        Launcher.main(args);

        URL url = new URL("http://localhost:18597/api/game/start");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");


        String toSend = "{bad_json: \"error_is_expected\"}";
        try (OutputStream output = con.getOutputStream()) {
            output.write(toSend.getBytes());
        }

        int status = con.getResponseCode();
        Assertions.assertEquals(status, 400, "Le statut HTTP n'est pas valide");
    }


    @org.junit.jupiter.api.Test
    void startWithGoodPost() throws IOException {
        String[] args = {"18598"};
        Launcher.main(args);

        URL url = new URL("http://localhost:18598/api/game/start");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");


        String toSend = "{\n" +
                "    \"id\": \"0c575465-21f6-43c9-8a2d-bc64c3ae6241\",\n" +
                "    \"url\": \"http://localhost:8795\",\n" +
                "    \"message\": \"I will crush you!\"\n" +
                "}";
        try (OutputStream output = con.getOutputStream()) {
            output.write(toSend.getBytes());
        }

        int status = con.getResponseCode();
        Assertions.assertEquals(status, 202, "Le statut HTTP n'est pas valide");
    }
}
