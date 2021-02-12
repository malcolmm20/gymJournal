package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GymJournalTest {
    private GymJournal journalA;
    private GymJournal journalB;
    private GymJournal journalC;
    private GymJournal journalD;
    private RoutineExercise exerciseA;
    private RoutineExercise exerciseB;
    private RoutineExercise exerciseC;
    private RoutineExercise exerciseD;
    private Routine routineA;
    private Routine routineB;
    private Routine routineC;
    private OpenWorkout workoutA;
    private OpenWorkout workoutB;
    private OpenWorkout workoutC;
    private WorkoutExercise workoutExerciseA;
    private WorkoutExercise workoutExerciseB;
    private WorkoutExercise workoutExerciseC;
    private WorkoutExercise workoutExerciseD;
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

    private void add() {
        addSets();
        addRoutineExercises();
        addExercises();
        addWorkouts();
        addRoutines();
    }

    private void addRoutines() {
        journalA.addRoutine(routineA);
        journalA.addRoutine(routineA);
        journalA.addRoutine(routineA);
        journalB.addRoutine(routineA);
        journalB.addRoutine(routineB);
        journalB.addRoutine(routineC);
        journalC.addRoutine(routineA);
        journalC.addRoutine(routineB);
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
    }

    private void addRoutineExercises() {
        routineA.addExercise(exerciseA);
        routineA.addExercise(exerciseB);
        routineA.addExercise(exerciseC);
        routineB.addExercise(exerciseA);
        routineB.addExercise(exerciseB);
        routineC.addExercise(exerciseC);
        routineC.addExercise(exerciseD);
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
    }

    private void instantiateRoutineExercises() {
        exerciseA = new RoutineExercise("bench press", 4);
        exerciseB = new RoutineExercise("bench press", 4);
        exerciseC = new RoutineExercise("bench press", 5);
        exerciseD = new RoutineExercise("leg press", 4);
    }

    private void instantiateRoutines() {
        routineA = new Routine("chest day");
        routineB = new Routine("chest day");
        routineC = new Routine("leg day");
    }

    private void instantiateWorkouts() {
        workoutA = new OpenWorkout();
        workoutB = new OpenWorkout();
        workoutC = new OpenWorkout();
    }

    private void instantiateExercises() {
        workoutExerciseA = new WorkoutExercise("bench press");
        workoutExerciseB = new WorkoutExercise("barbell back squat");
        workoutExerciseC = new WorkoutExercise("bench press");
        workoutExerciseD = new WorkoutExercise("bench press");
    }

    private void instantiateSets() {
        setA = new WorkoutSet(8, 150);
        setB = new WorkoutSet(12, 0);
        setC = new WorkoutSet(8, 150);
        setD = new WorkoutSet(10, 135);
        setE = new WorkoutSet(1, 200);
    }
}