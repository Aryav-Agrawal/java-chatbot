package chatbot;

public class ChatSession {

    private String lastQuestion = null;
    private String lastTopic = null;
    private String lastMatchedQuestion = null;

    public String getLastQuestion() {
        return lastQuestion;
    }

    public void setLastQuestion(String lastQuestion) {
        this.lastQuestion = lastQuestion;
    }

    public String getLastTopic() {
        return lastTopic;
    }

    public void setLastTopic(String lastTopic) {
        this.lastTopic = lastTopic;
    }

    public String getLastMatchedQuestion() {
        return lastMatchedQuestion;
    }

    public void setLastMatchedQuestion(String lastMatchedQuestion) {
        this.lastMatchedQuestion = lastMatchedQuestion;
    }
}