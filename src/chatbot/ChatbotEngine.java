package chatbot;

import storage.KnowledgeBase;
import storage.FileManager;

import java.util.HashMap;

public class ChatbotEngine {

    private static KnowledgeBase kb = new KnowledgeBase();

    private static String lastQuestion = null;
    private static String lastTopic = null;
    private static String lastMatchedQuestion = null;

    static {

        // load saved knowledge
        HashMap<String, String> data = FileManager.load();

        for (String q : data.keySet()) {
            kb.put(q, data.get(q));
        }

        // default knowledge
        kb.put("hi", "Hello!");
        kb.put("hello", "Hi there!");
        kb.put("bye", "Goodbye!");
        kb.put("how are you", "I'm doing great!");
    }

    public static String getResponse(String message) {

        // 🔥 keep original message (for storing answers correctly)
        String originalMessage = message;

        // process message for matching
        message = MessageProcessor.process(message);

        // ============================
        // 🔥 CORRECTION MODE (NEW)
        // ============================
        if (originalMessage.toLowerCase().startsWith("correct answer is") ||
            originalMessage.toLowerCase().startsWith("actually") ||
            originalMessage.toLowerCase().startsWith("no")) {

            if (lastMatchedQuestion != null) {

                String newAnswer = originalMessage
                        .replaceFirst("(?i)correct answer is", "")
                        .replaceFirst("(?i)actually", "")
                        .replaceFirst("(?i)no", "")
                        .trim();

                kb.put(lastMatchedQuestion, newAnswer);
                FileManager.save(kb.getAll());

                return "Got it! I've updated the answer.";
            }
        }

        // ============================
        // 🧠 LEARNING MODE
        // ============================
        if (lastQuestion != null) {

            // store original answer (preserves case)
            kb.put(lastQuestion, originalMessage);
            FileManager.save(kb.getAll());

            lastQuestion = null;

            return "Thanks! I learned something new.";
        }

        // ============================
        // 🧠 CONTEXT HANDLING
        // ============================
        if (message.equals("it") || message.equals("this") || message.equals("that")) {
            if (lastTopic != null) {
                message = lastTopic;
            }
        }

        // ============================
        // ✅ EXACT MATCH
        // ============================
        if (kb.contains(message)) {
            lastTopic = message;
            lastMatchedQuestion = message;   // 🔥 store for correction
            return kb.get(message);
        }

        // ============================
        // 🔍 SIMILARITY + PARTIAL MATCH
        // ============================
        double bestScore = 0;
        String bestAnswer = null;
        String bestQuestion = null;

        for (String question : kb.getAll().keySet()) {

            double score = MessageProcessor.similarity(message, question);

            // partial match boost
            if (question.contains(message) || message.contains(question)) {
                score += 0.3;
            }

            if (score > bestScore) {
                bestScore = score;
                bestAnswer = kb.get(question);
                bestQuestion = question;
            }
        }

        // ============================
        // ✅ THRESHOLD MATCH
        // ============================
        if (bestScore > 0.4) {
            lastTopic = bestQuestion;
            lastMatchedQuestion = bestQuestion;   // 🔥 store for correction
            return bestAnswer;
        }

        // ============================
        // ❓ UNKNOWN → LEARNING MODE
        // ============================
        lastQuestion = message;
        lastTopic = message;

        return "I don't know the answer. Please teach me.";
    }
}