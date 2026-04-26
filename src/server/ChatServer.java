package server;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class ChatServer {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // route for homepage
        server.createContext("/", new WebHandler());

        // route for chatbot messages
        server.createContext("/chat", new ChatHandler());

        server.setExecutor(null);

        System.out.println("Server started: http://localhost:8080");

        server.start();
    }
}