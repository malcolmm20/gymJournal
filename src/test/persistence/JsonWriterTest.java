package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// modelled after JsonWriterTest from JsonSerializationDemo
public class JsonWriterTest {
    private GymJournal gymJournal;
    private OpenWorkout ow;
    private WorkoutExercise we1;
    private WorkoutExercise we2;
    private WorkoutSet ws;
    private Routine r;
    private RoutineExercise re1;
    private RoutineExercise re2;

    @BeforeEach
    void setUp() {
        gymJournal = new GymJournal();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyGymJournal() {
        try {
            GymJournal gymJournal = new GymJournal();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGymJournal.json");
            writer.open();
            writer.write(gymJournal);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGymJournal.json");
            HashMap<String, ArrayList<Writable>> gjHashMap = reader.read();
            assertEquals(0, gjHashMap.get("workouts").size());
            assertEquals(0, gjHashMap.get("routines").size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            initialize();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGymJournal.json");
            writer.open();
            writer.write(gymJournal);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGymJournal.json");
            HashMap<String, ArrayList<Writable>> gjHashMap = reader.read();
            loadRoutines(gjHashMap.get("routines"));
            loadWorkouts(gjHashMap.get("workouts"));
            assertEquals(1, gjHashMap.get("workouts").size());
            assertEquals(1, gjHashMap.get("routines").size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    private void initialize() {
        ow = new OpenWorkout();
        we1 = new WorkoutExercise("bench press");
        we2 = new WorkoutExercise("chest fly");
        ws = new WorkoutSet(10, 100);
        r = new Routine("chest day");
        re1 = new RoutineExercise("bench press", 3);
        re2 = new RoutineExercise("chest fly", 2);
        we1.addSet(ws);
        we2.addSet(ws);
        ow.addExercise(we1);
        ow.addExercise(we2);
        r.addExercise(re1);
        r.addExercise(re2);
        gymJournal.addRoutine(r);
        gymJournal.addWorkout(ow);
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
