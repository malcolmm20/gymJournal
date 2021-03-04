package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GymJournalTest {
    private GymJournal journalA;
    private GymJournal journalB;
    private GymJournal journalC;
    private GymJournal journalD;
    private GymJournal journalE;
    private GymJournal journalF;
    private RoutineExercise exerciseA;
    private RoutineExercise exerciseB;
    private RoutineExercise exerciseC;
    private RoutineExercise exerciseD;
    private RoutineExercise exerciseE;
    private Routine routineA;
    private Routine routineB;
    private Routine routineC;
    private Routine routineD;
    private OpenWorkout workoutA;
    private OpenWorkout workoutB;
    private OpenWorkout workoutC;
    private OpenWorkout workoutD;
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
        add();
    }

    @Test
    void testToJson() {
        JSONObject jsonA = new JSONObject();
        JSONObject jsonB = new JSONObject();
        initializeJson(jsonA, jsonB);
        assertTrue(jsonA.similar(journalF.toJson()));
        assertFalse(journalF.toJson().similar(jsonB));
    }

    private void initializeJson(JSONObject jsonA, JSONObject jsonB) {
        JSONArray jsonWorkoutArray = new JSONArray();
        JSONArray jsonRoutineArray = new JSONArray();
        JSONArray workoutSets = new JSONArray();
        JSONObject routine = new JSONObject();
        JSONArray routineExercises = new JSONArray();
        JSONArray workoutExercises = new JSONArray();
        JSONObject set = new JSONObject();
        JSONObject routineExercise = new JSONObject();
        JSONObject workout = new JSONObject();
        JSONObject workoutExercise = new JSONObject();
        workout.put("date", LocalDate.now());
        setsValues(set, workoutSets);
        workoutExercise.put("sets", workoutSets);
        workoutExercise.put("exercise name", "e");
        workoutExercises.put(workoutExercise);
        workout.put("exercises", workoutExercises);
        routineExercisesValues(routineExercises, routineExercise);
        routine.put("exercises", routineExercises);
        routine.put("name", "d");
        jsonRoutineArray.put(routine);
        jsonWorkoutArray.put(workout);
        jsonA.put("workout history", jsonWorkoutArray);
        jsonA.put("routines", jsonRoutineArray);
        jsonB.put("workout history", jsonWorkoutArray);
    }

    private void setsValues(JSONObject set, JSONArray workoutSets) {
        set.put("reps", 1);
        set.put("weight", 200);
        workoutSets.put(set);
    }

    private void routineExercisesValues(JSONArray routineExercises, JSONObject routineExercise) {
        routineExercise.put("sets", 1);
        routineExercise.put("exercise name", "e");
        routineExercises.put(routineExercise);
    }

    @Test
    public void testEquals() {
        journalE = new GymJournal();
        assertEquals(journalA, journalA);
        assertEquals(journalA, journalB);
        assertNotEquals(journalA, journalC);
        assertNotEquals(journalA, journalD);
        journalE.addWorkout(workoutA);
        journalE.addWorkout(workoutB);
        journalE.addWorkout(workoutC);
        journalE.addRoutine(routineB);
        journalE.addRoutine(routineC);
        assertNotEquals(journalA, journalE);
    }

    @Test
    public void testHashCode() {
        assertEquals(journalA.hashCode(), journalB.hashCode());
        assertNotEquals(journalA.hashCode(), journalC.hashCode());
    }

    @Test
    public void testFindRoutine() {
        assertEquals(routineA, journalA.findRoutine("routine 1"));
        assertEquals(routineB, journalC.findRoutine("routine 2"));
    }

    @Test
    public void testRoutineString() {
        assertEquals("routine 2\n" +
                "bench press: 4 sets\n" +
                "bench press: 4 sets\n" +
                "\n" +
                "routine 3\n" +
                "bench press: 5 sets\n" +
                "leg press: 4 sets\n" +
                "\n" +
                "routine 1\n" +
                "bench press: 4 sets\n" +
                "bench press: 4 sets\n" +
                "bench press: 5 sets\n\n", journalA.routineString());
        assertEquals("routine 2\n" +
                "bench press: 4 sets\n" +
                "bench press: 4 sets\n" +
                "\n" +
                "routine 1\n" +
                "bench press: 4 sets\n" +
                "bench press: 4 sets\n" +
                "bench press: 5 sets\n" +
                "\n", journalC.routineString());
    }

    @Test
    public void testWorkoutString() {
        assertEquals(workoutA.toString() + "\n" + workoutB.toString()
                + "\n", journalB.workoutString(2));
        assertEquals(workoutA.toString() + "\n" + workoutB.toString()
                + "\n" + workoutC.toString() + "\n", journalB.workoutString(4));
        assertEquals(workoutA.toString() + "\n"
                + workoutC.toString() + "\n", journalC.workoutString(3));
    }

    @Test
    public void testDisplayPersonalBests() {
        assertEquals("PERSONAL BESTS\n" +
                "--------------\n" +
                "bench press: 1 reps, 200 lbs\n" +
                "barbell back squat: 8 reps, 150 lbs\n", journalA.displayPersonalBests());
        assertEquals("PERSONAL BESTS\n" +
                "--------------\n" +
                "bench press: 1 reps, 200 lbs\n" +
                "barbell back squat: 8 reps, 150 lbs\n", journalC.displayPersonalBests());
    }

    @Test
    public void testDisplayOneRepMaxes() {
        assertEquals("ONE REP MAXES\n" +
                        "-------------\n" +
                        "bench press: 200.0 lbs\n" +
                        "barbell back squat: 190.0 lbs\n"
                , journalA.displayOneRepMaxes());
        assertEquals("ONE REP MAXES\n" +
                "-------------\n" +
                "bench press: 200.0 lbs\n" +
                "barbell back squat: 190.0 lbs\n", journalC.displayOneRepMaxes());
    }

    @Test
    public void testUpdateOneRepMaxesLarger() {
        assertEquals("ONE REP MAXES\n" +
                        "-------------\n" +
                        "bench press: 200.0 lbs\n" +
                        "barbell back squat: 190.0 lbs\n"
                , journalA.displayOneRepMaxes());
        OpenWorkout workoutD = new OpenWorkout();
        WorkoutExercise workoutExerciseE = new WorkoutExercise("bench press");
        WorkoutSet setF = new WorkoutSet(2, 250);
        workoutExerciseE.addSet(setF);
        workoutD.addExercise(workoutExerciseE);
        journalA.addWorkout(workoutD);
        assertEquals("ONE REP MAXES\n" +
                        "-------------\n" +
                        "bench press: 266.67 lbs\n" +
                        "barbell back squat: 190.0 lbs\n"
                , journalA.displayOneRepMaxes());
    }

    @Test
    public void testUpdateOneRepMaxesNull() {
        assertEquals("ONE REP MAXES\n" +
                        "-------------\n" +
                        "bench press: 200.0 lbs\n" +
                        "barbell back squat: 190.0 lbs\n"
                , journalA.displayOneRepMaxes());
        OpenWorkout workoutD = new OpenWorkout();
        WorkoutExercise workoutExerciseE = new WorkoutExercise("leg press");
        WorkoutSet setF = new WorkoutSet(2, 250);
        workoutExerciseE.addSet(setF);
        workoutD.addExercise(workoutExerciseE);
        journalA.addWorkout(workoutD);
        assertEquals("ONE REP MAXES\n" +
                        "-------------\n" +
                        "leg press: 266.67 lbs\n" +
                        "bench press: 200.0 lbs\n" +
                        "barbell back squat: 190.0 lbs\n"
                , journalA.displayOneRepMaxes());
    }

    @Test
    public void testUpdatePersonalBestsLarger() {
        assertEquals("PERSONAL BESTS\n" +
                        "--------------\n" +
                        "bench press: 1 reps, 200 lbs\n" +
                        "barbell back squat: 8 reps, 150 lbs\n"
                , journalA.displayPersonalBests());
        OpenWorkout workoutD = new OpenWorkout();
        WorkoutExercise workoutExerciseE = new WorkoutExercise("bench press");
        WorkoutSet setF = new WorkoutSet(2, 250);
        workoutExerciseE.addSet(setF);
        workoutD.addExercise(workoutExerciseE);
        journalA.addWorkout(workoutD);
        assertEquals("PERSONAL BESTS\n" +
                        "--------------\n" +
                        "bench press: 2 reps, 250 lbs\n" +
                        "barbell back squat: 8 reps, 150 lbs\n"
                , journalA.displayPersonalBests());
    }

    @Test
    public void testUpdatePersonalBestsEqualMoreSets() {
        assertEquals("PERSONAL BESTS\n" +
                        "--------------\n" +
                        "bench press: 1 reps, 200 lbs\n" +
                        "barbell back squat: 8 reps, 150 lbs\n"
                , journalA.displayPersonalBests());
        OpenWorkout workoutD = new OpenWorkout();
        WorkoutExercise workoutExerciseE = new WorkoutExercise("bench press");
        WorkoutSet setF = new WorkoutSet(2, 200);
        workoutExerciseE.addSet(setF);
        workoutD.addExercise(workoutExerciseE);
        journalA.addWorkout(workoutD);
        assertEquals("PERSONAL BESTS\n" +
                        "--------------\n" +
                        "bench press: 2 reps, 200 lbs\n" +
                        "barbell back squat: 8 reps, 150 lbs\n"
                , journalA.displayPersonalBests());
    }

    @Test
    public void testUpdatePersonalBestsEqual() {
        assertEquals("PERSONAL BESTS\n" +
                        "--------------\n" +
                        "bench press: 1 reps, 200 lbs\n" +
                        "barbell back squat: 8 reps, 150 lbs\n"
                , journalA.displayPersonalBests());
        OpenWorkout workoutD = new OpenWorkout();
        WorkoutExercise workoutExerciseE = new WorkoutExercise("bench press");
        WorkoutSet setF = new WorkoutSet(1, 200);
        workoutExerciseE.addSet(setF);
        workoutD.addExercise(workoutExerciseE);
        journalA.addWorkout(workoutD);
        assertEquals("PERSONAL BESTS\n" +
                        "--------------\n" +
                        "bench press: 1 reps, 200 lbs\n" +
                        "barbell back squat: 8 reps, 150 lbs\n"
                , journalA.displayPersonalBests());
    }

    @Test
    public void testUpdatePersonalBestsLess() {
        assertEquals("PERSONAL BESTS\n" +
                        "--------------\n" +
                        "bench press: 1 reps, 200 lbs\n" +
                        "barbell back squat: 8 reps, 150 lbs\n"
                , journalA.displayPersonalBests());
        OpenWorkout workoutD = new OpenWorkout();
        WorkoutExercise workoutExerciseE = new WorkoutExercise("bench press");
        WorkoutSet setF = new WorkoutSet(1, 190);
        workoutExerciseE.addSet(setF);
        workoutD.addExercise(workoutExerciseE);
        journalA.addWorkout(workoutD);
        assertEquals("PERSONAL BESTS\n" +
                        "--------------\n" +
                        "bench press: 1 reps, 200 lbs\n" +
                        "barbell back squat: 8 reps, 150 lbs\n"
                , journalA.displayPersonalBests());
    }

    private void add() {
        addSets();
        addRoutineExercises();
        addExercises();
        addWorkouts();
        addRoutines();
    }

    private void addRoutines() {
        journalA.addRoutine(routineA);
        journalA.addRoutine(routineB);
        journalA.addRoutine(routineC);
        journalB.addRoutine(routineA);
        journalB.addRoutine(routineB);
        journalB.addRoutine(routineC);
        journalC.addRoutine(routineA);
        journalC.addRoutine(routineB);
        journalF.addRoutine(routineD);
    }

    private void addWorkouts() {
        journalA.addWorkout(workoutA);
        journalA.addWorkout(workoutB);
        journalA.addWorkout(workoutC);
        journalB.addWorkout(workoutA);
        journalB.addWorkout(workoutB);
        journalB.addWorkout(workoutC);
        journalC.addWorkout(workoutA);
        journalC.addWorkout(workoutC);
        journalF.addWorkout(workoutD);
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
        workoutD.addExercise(workoutExerciseE);
    }

    private void addRoutineExercises() {
        routineA.addExercise(exerciseA);
        routineA.addExercise(exerciseB);
        routineA.addExercise(exerciseC);
        routineB.addExercise(exerciseA);
        routineB.addExercise(exerciseB);
        routineC.addExercise(exerciseC);
        routineC.addExercise(exerciseD);
        routineD.addExercise(exerciseE);
    }

    private void addSets() {
        addSetExerciseA();
        addSetExerciseB();
        addSetExerciseC();
        addSetExerciseD();
    }

    private void addSetExerciseA() {
        workoutExerciseA.addSet(setA);
        workoutExerciseA.addSet(setB);
        workoutExerciseA.addSet(setC);
        workoutExerciseA.addSet(setD);
        workoutExerciseA.addSet(setE);
    }

    private void addSetExerciseB() {
        workoutExerciseB.addSet(setA);
        workoutExerciseB.addSet(setB);
    }

    private void addSetExerciseC() {
        workoutExerciseC.addSet(setA);
        workoutExerciseC.addSet(setB);
        workoutExerciseC.addSet(setC);
        workoutExerciseC.addSet(setD);
        workoutExerciseC.addSet(setE);
    }

    private void addSetExerciseD() {
        workoutExerciseD.addSet(setC);
        workoutExerciseD.addSet(setD);
        workoutExerciseD.addSet(setE);
        workoutExerciseD.addSet(setD);
        workoutExerciseD.addSet(setE);
        workoutExerciseE.addSet(setE);
    }


    private void instantiate() {
        instantiateJournals();
        instantiateRoutineExercises();
        instantiateRoutines();
        instantiateWorkouts();
        instantiateExercises();
        instantiateSets();
    }

    private void instantiateJournals() {
        journalA = new GymJournal();
        journalB = new GymJournal();
        journalC = new GymJournal();
        journalF = new GymJournal();
    }

    private void instantiateRoutineExercises() {
        exerciseA = new RoutineExercise("bench press", 4);
        exerciseB = new RoutineExercise("bench press", 4);
        exerciseC = new RoutineExercise("bench press", 5);
        exerciseD = new RoutineExercise("leg press", 4);
        exerciseE = new RoutineExercise("e", 1);
    }

    private void instantiateRoutines() {
        routineA = new Routine("routine 1");
        routineB = new Routine("routine 2");
        routineC = new Routine("routine 3");
        routineD = new Routine("d");
    }

    private void instantiateWorkouts() {
        workoutA = new OpenWorkout();
        workoutB = new OpenWorkout();
        workoutC = new OpenWorkout();
        workoutD = new OpenWorkout();
    }

    private void instantiateExercises() {
        workoutExerciseA = new WorkoutExercise("bench press");
        workoutExerciseB = new WorkoutExercise("barbell back squat");
        workoutExerciseC = new WorkoutExercise("bench press");
        workoutExerciseD = new WorkoutExercise("bench press");
        workoutExerciseE = new WorkoutExercise("e");
    }

    private void instantiateSets() {
        setA = new WorkoutSet(8, 150);
        setB = new WorkoutSet(12, 0);
        setC = new WorkoutSet(8, 150);
        setD = new WorkoutSet(10, 135);
        setE = new WorkoutSet(1, 200);
    }
}