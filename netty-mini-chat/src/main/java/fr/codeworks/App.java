package fr.codeworks;


import fr.codeworks.server.ChatServer;

import java.util.logging.Logger;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main( String[] args ) throws Exception {
        LOGGER.info("App starting ....");
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;
        new ChatServer(port).run();

    }
}
