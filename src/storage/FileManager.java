package storage;

import java.io.*;
import java.util.HashMap;

public class FileManager {

    private static final String FILE_PATH = "data/knowledge.txt";

    public static void save(HashMap<String, String> knowledge) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {

            for (String key : knowledge.keySet()) {
                writer.write(key + ":" + knowledge.get(key));
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, String> load() {

        HashMap<String, String> map = new HashMap<>();

        File file = new File(FILE_PATH);

        if (!file.exists()) return map;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(":", 2);

                if (parts.length == 2) {
                    map.put(parts[0], parts[1]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}