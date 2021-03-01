package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

/*
 * Represents a reader that reads a GymJournal from JSON data stored in a file
 * Cited from JsonReader in JsonSerializationDemo
 */
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads and returns gym journal from a file
    // throws IOException if error occurs while reading file
    public GymJournal read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGymJournal(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses gym journal from JSON Object and returns it
    private GymJournal parseGymJournal(JSONObject jsonObject) {
        GymJournal gj = new GymJournal();
        //addWorkoutHistory(gj, jsonObject);
        //addRoutines(gj, jsonObject);
        //addPersonalBests(gj, jsonObject);
        //addOneRepMaxes(gj, jsonObject);
        return gj;
    }

}
