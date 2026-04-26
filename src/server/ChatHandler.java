package server;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import chatbot.ChatbotEngine;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ChatHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        InputStream is = exchange.getRequestBody();
        String message = new String(is.readAllBytes());

        String response = ChatbotEngine.getResponse(message);

        exchange.sendResponseHeaders(200, response.length());

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}