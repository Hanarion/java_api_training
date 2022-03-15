package fr.lernejo.navy_battle;

public class Launcher {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Merci de préciser le port");
            return;
        }
        int port;
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Ce n'est pas un port valide");
            return;
        }

        if (port > 0 && port < 65535) {
            NavyBattleServer server = new NavyBattleServer("0.0.0.0", port);
            server.startHttpServer();
        } else {
            System.out.println("Merci d'indiquer un port valide");
            return;
        }

    }
}
