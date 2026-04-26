package chatbot;

import java.util.HashMap;

public class SessionManager {

    private static HashMap<String, ChatSession> sessions = new HashMap<>();

    public static ChatSession getSession(String chatId) {

        if (!sessions.containsKey(chatId)) {
            sessions.put(chatId, new ChatSession());
        }

        return sessions.get(chatId);
    }
}