package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class OpenWorkoutTest {
    private OpenWorkout workoutA;
    private OpenWorkout workoutB;
    private OpenWorkout workoutC;
    private OpenWorkout workoutD;
    private OpenWorkout workoutE;
    private WorkoutExercise workoutExerciseA;
    private WorkoutExercise workoutExerciseB;
    private WorkoutExercise workoutExerciseC;
    private WorkoutExercise workoutExerciseD;
    private WorkoutExercise workoutExerciseE;
    private WorkoutSet setA;
    private WorkoutSet setB;
    private WorkoutSet setC;
    private WorkoutSet setD;
    private WorkoutSet setE;


    @BeforeEach
    void setUp() {
        instantiate();
        addSets();
        addExercises();
    }

    @Test
    void testToJson() {
        JSONObject jsonA = new JSONObject();
        JSONObject jsonB = new JSONObject();
        initializeJson(jsonA, jsonB);
        assertTrue(workoutE.toJson().similar(jsonA));
        assertFalse(workoutE.toJson().similar(jsonB));
    }

    private void initializeJson(JSONObject jsonA, JSONObject jsonB) {
        JSONArray workoutExercises = new JSONArray();
        JSONArray workoutSets = new JSONArray();
        JSONObject set = new JSONObject();
        JSONObject workoutExercise = new JSONObject();
        jsonA.put("date", LocalDate.now());
        set.put("reps", 2);
        set.put("weight", 200);
        workoutSets.put(set);
        workoutExercise.put("sets", workoutSets);
        workoutExercise.put("exercise name", "e");
        workoutExercises.put(workoutExercise);
        jsonA.put("exercises", workoutExercises);
        jsonB.put("date", LocalDate.now());
    }

    @Test
    void testEquals() {
        assertEquals(workoutA, workoutA);
        assertEquals(workoutA, workoutC);
        assertNotEquals(workoutA, workoutD);
        assertNotEquals(workoutA, workoutB);
        workoutB.addDays(4);
        assertNotEquals(workoutA, workoutB);
        workoutC.addDays(4);
        assertNotEquals(workoutA, workoutC);
    }

    @Test
    void testHashCode() {
        assertEquals(workoutA.hashCode(), workoutA.hashCode());
        assertEquals(workoutA.hashCode(), workoutC.hashCode());
        assertNotEquals(workoutA.hashCode(), workoutB.hashCode());
    }

    @Test
    void testAddExercise() {
        assertEquals(workoutA, workoutC);
        workoutA.addExercise(workoutExerciseA);
        assertNotEquals(workoutA, workoutC);
        workoutC.addExercise(workoutExerciseA);
        assertEquals(workoutA, workoutC);
    }

    @Test
    void testToString() {
        assertEquals(workoutA.getDate() + " workout.\n"
                + workoutExerciseA.toString() + "\n" + workoutExerciseB.toString()
                + "\n" + workoutExerciseC.toString() + "\n", workoutA.toString());
        assertEquals(workoutB.getDate() + " workout.\n"
                + workoutExerciseA.toString() + "\n" + workoutExerciseB.toString()
                + "\n" + workoutExerciseD.toString() + "\n" + workoutExerciseD.toString()
                + "\n", workoutB.toString());
    }

    @Test
    void testHeaviestSets() {
        HashMap<String, WorkoutSet> heaviestSetHashMap = new HashMap<>();
        heaviestSetHashMap.put("bench press", setE);
        heaviestSetHashMap.put("barbell back squat", setB);
        heaviestSetHashMap.put("leg press", setE);
        assertEquals(heaviestSetHashMap, workoutA.heaviestSets());
    }

    @Test
    public void testGetDate() {
        LocalDate compDate = LocalDate.now();
        String date = "2016-08-16";
        LocalDate newDate = LocalDate.parse(date);
        assertEquals(compDate, workoutA.getDate());
        workoutB.setDate(newDate);
        assertEquals(newDate, workoutB.getDate());
    }

    @Test
    void testSetDate() {
        LocalDate compDate = LocalDate.now();
        String date = "2016-08-16";
        LocalDate newDate = LocalDate.parse(date);
        assertEquals(compDate, workoutA.getDate());
        workoutA.setDate(newDate);
        assertEquals(newDate, workoutA.getDate());
    }

    private void instantiate() {
        workoutA = new OpenWorkout();
        workoutB = new OpenWorkout();
        workoutC = new OpenWorkout();
        workoutE = new OpenWorkout();
        workoutExerciseA = new WorkoutExercise("bench press");
        workoutExerciseB = new WorkoutExercise("barbell back squat");
        workoutExerciseC = new WorkoutExercise("leg press");
        workoutExerciseD = new WorkoutExercise("shoulder press");
        workoutExerciseE = new WorkoutExercise("e");
        setA = new WorkoutSet(10, 100);
        setB = new WorkoutSet(8, 120);
        setC = new WorkoutSet(3, 190);
        setD = new WorkoutSet(7, 140);
        setE = new WorkoutSet(2, 200);
    }

    private void addSets() {
        workoutExerciseA.addSet(setA);
        workoutExerciseA.addSet(setB);
        workoutExerciseA.addSet(setC);
        workoutExerciseA.addSet(setD);
        workoutExerciseA.addSet(setE);
        workoutExerciseC.addSet(setA);
        workoutExerciseC.addSet(setB);
        workoutExerciseC.addSet(setC);
        workoutExerciseC.addSet(setD);
        workoutExerciseC.addSet(setE);
        workoutExerciseB.addSet(setA);
        workoutExerciseB.addSet(setB);
        workoutExerciseD.addSet(setC);
        workoutExerciseD.addSet(setD);
        workoutExerciseD.addSet(setE);
        workoutExerciseD.addSet(setD);
        workoutExerciseD.addSet(setE);
        workoutExerciseE.addSet(setE);
    }

    private void addExercises() {
        workoutA.addExercise(workoutExerciseA);
        workoutA.addExercise(workoutExerciseB);
        workoutA.addExercise(workoutExerciseC);
        workoutC.addExercise(workoutExerciseA);
        workoutC.addExercise(workoutExerciseB);
        workoutC.addExercise(workoutExerciseC);
        workoutB.addExercise(workoutExerciseA);
        workoutB.addExercise(workoutExerciseB);
        workoutB.addExercise(workoutExerciseD);
        workoutB.addExercise(workoutExerciseD);
        workoutE.addExercise(workoutExerciseE);
    }
}
