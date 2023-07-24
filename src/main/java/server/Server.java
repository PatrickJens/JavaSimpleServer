package server;

import server.config.ConfigurationManager;

public class Server {
    public static void main(String[] args){
        System.out.println("Server started...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/HttpConfig.json");
    }
}
