package storage;

import java.util.HashMap;

public class KnowledgeBase {

    private HashMap<String, String> knowledge = new HashMap<>();

    public String get(String question) {
        return knowledge.get(question.toLowerCase());
    }

    public void put(String question, String answer) {
        knowledge.put(question.toLowerCase(), answer);
    }

    public boolean contains(String question) {
        return knowledge.containsKey(question.toLowerCase());
    }

    public HashMap<String, String> getAll() {
        return knowledge;
    }
}