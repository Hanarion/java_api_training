package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.game.Player;

import java.util.UUID;

public class Launcher {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Merci de préciser le port");
            return;
        }
        String url = null;
        if (args.length > 1) {
            url = args[1];
        }
        int port;
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Ce n'est pas un port valide");
            return;
        }

        if (port > 0 && port < 65535) {
            String ip = "localhost";
            Player player = new Player(UUID.randomUUID(), "http://" + ip + ':' + port, "May the best code win");
            NavyBattleServer server = new NavyBattleServer(ip, port, player);
            if (url != null) {
                Player opponnent = new Player().getFromUrl(url, player);
                if (opponnent == null)
                    return;
                server.setOponnent(opponnent);

            }
            server.startHttpServer();
        } else {
            System.out.println("Merci d'indiquer un port valide");
            return;
        }

    }
}
