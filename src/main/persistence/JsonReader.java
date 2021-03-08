package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

import org.json.*;

/*
 * Represents a reader that reads a GymJournal from JSON data stored in a file
 * Cited from JsonReader in JsonSerializationDemo
 */
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads and returns gym journal from a file
    // throws IOException if error occurs while reading file
    public HashMap<String, ArrayList<Writable>> read() throws IOException {
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
    private HashMap<String, ArrayList<Writable>> parseGymJournal(JSONObject jsonObject) {
        HashMap<String, ArrayList<Writable>> gjHashMap = new HashMap<>();
        ArrayList routines = new ArrayList<Writable>();
        ArrayList workouts = new ArrayList<Writable>();
        gjHashMap.put("routines", routines);
        gjHashMap.put("workouts", workouts);
        addWorkouts(gjHashMap, jsonObject);
        addRoutines(gjHashMap, jsonObject);
        return gjHashMap;
    }

    // MODIFIES: gj
    // EFFECTS: parses workout history from JSON object and adds to gj
    private void addWorkouts(HashMap<String, ArrayList<Writable>> gj, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("workout history");
        for (Object json : jsonArray) {
            JSONObject nextWorkout = (JSONObject) json;
            addWorkout(gj, nextWorkout);
        }
    }

    // MODIFIES: gj
    // EFFECTS: adds workout to workout array in gym journal hash map
    private void addWorkout(HashMap<String, ArrayList<Writable>> gj, JSONObject nextWorkout) {
        OpenWorkout ow = new OpenWorkout();
        LocalDate date = LocalDate.parse(nextWorkout.getString("date"));
        ow.setDate(date);
        JSONArray jsonArray = nextWorkout.getJSONArray("exercises");
        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            addExercise(ow, nextExercise);
        }
        gj.get("workouts").add(ow);
    }

    // MODIFIES: ow
    // EFFECTS: adds exercise to workout
    private void addExercise(OpenWorkout ow, JSONObject nextExercise) {
        WorkoutExercise e = new WorkoutExercise(nextExercise.getString("exercise name"));
        JSONArray jsonArray = nextExercise.getJSONArray("sets");
        for (Object json : jsonArray) {
            JSONObject nextSet = (JSONObject) json;
            addSet(e, nextSet);
        }
        ow.addExercise(e);
    }

    // MODIFIES: e
    // EFFECTS: adds set to exercise
    private void addSet(WorkoutExercise e, JSONObject nextSet) {
        WorkoutSet s = new WorkoutSet(nextSet.getInt("reps"), nextSet.getInt("weight"));
        e.addSet(s);
    }

    // MODIFIES: gj
    // EFFECTS: parses routines from JSONObject and adds to gj
    private void addRoutines(HashMap<String, ArrayList<Writable>> gj, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("routines");
        for (Object json : jsonArray) {
            JSONObject nextRoutine = (JSONObject) json;
            addRoutine(gj, nextRoutine);
        }
    }

    // MODIFIES: gj
    // EFFECTS: adds routine to routine array in gj hashmap
    private void addRoutine(HashMap<String, ArrayList<Writable>> gj, JSONObject nextRoutine) {
        JSONArray jsonArray = nextRoutine.getJSONArray("exercises");
        Routine r = new Routine(nextRoutine.getString("name"));
        for (Object json : jsonArray) {
            JSONObject nextRoutineExercise = (JSONObject) json;
            addRoutineExercise(r, nextRoutineExercise);
        }
        gj.get("routines").add(r);
    }

    // MODIFIES: r
    // EFFECTS: adds exercise to routine
    private void addRoutineExercise(Routine r, JSONObject nextRoutineExercise) {
        RoutineExercise re = new RoutineExercise(nextRoutineExercise.getString("exercise name"),
                nextRoutineExercise.getInt("sets"));
        r.addExercise(re);
    }
}
