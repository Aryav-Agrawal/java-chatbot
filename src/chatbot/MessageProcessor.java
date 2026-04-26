package chatbot;

import java.util.*;
import java.io.*;

public class MessageProcessor {

    // store synonyms loaded from file
    private static Map<String,String> synonyms = new HashMap<>();

    // =============================
    // LOAD SYNONYMS FROM FILE
    // =============================
    static{

        try(BufferedReader reader =
            new BufferedReader(new FileReader("data/synonyms.txt"))){

            String line;

            while((line = reader.readLine()) != null){

                String[] parts = line.split(":",2);

                if(parts.length == 2){
                    synonyms.put(parts[0], parts[1]);
                }

            }

        }catch(Exception e){
            System.out.println("Synonyms file not found.");
        }

    }

    // =============================
    // NORMALIZE INPUT
    // =============================
    public static String normalize(String input) {

        return input
                .toLowerCase()
                .replaceAll("[^a-z0-9 ]", "")
                .trim();
    }

    // =============================
    // REMOVE STOPWORDS
    // =============================
    public static String removeStopWords(String input) {

        List<String> stopWords = Arrays.asList(
                "what","is","are","the","a","an","tell","me","about"
        );

        StringBuilder result = new StringBuilder();

        for(String word : input.split(" ")){

            if(!stopWords.contains(word)){
                result.append(word).append(" ");
            }

        }

        return result.toString().trim();
    }

    // =============================
    // APPLY SYNONYMS
    // =============================
    public static String applySynonyms(String input){

        for(String key : synonyms.keySet()){

            input = input.replace(key, synonyms.get(key));

        }

        return input;
    }

    // =============================
    // MAIN PIPELINE
    // =============================
    public static String process(String input){

        input = normalize(input);
        input = applySynonyms(input);
        input = removeStopWords(input);

        return input;
    }

    // =============================
    // SIMILARITY CALCULATION
    // =============================
    public static double similarity(String a, String b){

        Set<String> wordsA = new HashSet<>(Arrays.asList(a.split(" ")));
        Set<String> wordsB = new HashSet<>(Arrays.asList(b.split(" ")));

        Set<String> intersection = new HashSet<>(wordsA);
        intersection.retainAll(wordsB);

        Set<String> union = new HashSet<>(wordsA);
        union.addAll(wordsB);

        if(union.size() == 0) return 0;

        return (double) intersection.size() / union.size();
    }
}