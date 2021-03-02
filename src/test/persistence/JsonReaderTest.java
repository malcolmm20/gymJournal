package persistence;

import model.GymJournal;
import model.OpenWorkout;
import model.Routine;
import model.WorkoutExercise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// modelled after JsonReaderTest from JsonSerializationDemo
public class JsonReaderTest {
    private GymJournal gymJournal;

    @BeforeEach
    void setUp() {
        gymJournal = new GymJournal();
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            HashMap<String, ArrayList<Writable>> gjHashMap = reader.read();
            loadRoutines(gjHashMap.get("routines"));
            loadWorkouts(gjHashMap.get("workouts"));
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGymJournal() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGymJournal.json");
        try {
            HashMap<String, ArrayList<Writable>> gjHashMap = reader.read();
            assertEquals(0, gjHashMap.get("workouts").size());
            assertEquals(0, gjHashMap.get("routines").size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGymJournal() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGymJournal.json");
        try {
            HashMap<String, ArrayList<Writable>> gjHashMap = reader.read();
            loadRoutines(gjHashMap.get("routines"));
            loadWorkouts(gjHashMap.get("workouts"));
            assertEquals(1, gjHashMap.get("workouts").size());
            assertEquals(1, gjHashMap.get("routines").size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // MODIFIES: gymJournal
    // EFFECTS: loads workouts from arraylist to gym journal
    private void loadWorkouts(ArrayList<Writable> workouts) {
        for (Writable w : workouts) {
            OpenWorkout ow = (OpenWorkout) w;
            gymJournal.addWorkout(ow);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads routines from array list to gym journal
    private void loadRoutines(ArrayList<Writable> routines) {
        for (Writable w : routines) {
            Routine r = (Routine) w;
            gymJournal.addRoutine(r);
        }
    }
}
