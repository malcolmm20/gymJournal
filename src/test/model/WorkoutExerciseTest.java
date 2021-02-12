package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkoutExerciseTest {
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
    private WorkoutSet setF;

    @BeforeEach
    void setup() {
        instantiate();
        addSetExerciseA();
        addSetExerciseB();
        addSetExerciseC();
        addSetExerciseD();
    }

    @Test
    void testEquals() {
         assertEquals(workoutExerciseA, workoutExerciseA);
         assertEquals(workoutExerciseA, workoutExerciseC);
         assertNotEquals(workoutExerciseA, workoutExerciseE);
         assertNotEquals(workoutExerciseA, workoutExerciseD);
         assertNotEquals(workoutExerciseC, workoutExerciseB);
    }

    @Test
    void testHashCode() {
        assertEquals(workoutExerciseA.hashCode(), workoutExerciseA.hashCode());
        assertEquals(workoutExerciseA.hashCode(), workoutExerciseC.hashCode());
        assertNotEquals(workoutExerciseA.hashCode(), workoutExerciseD.hashCode());
    }

    @Test
    void testHeaviestSet() {
        workoutExerciseA.addSet(setF);
        assertEquals(setF, workoutExerciseA.heaviestSet());
        assertEquals(setB, workoutExerciseB.heaviestSet());
        assertEquals(setE, workoutExerciseD.heaviestSet());
    }

    @Test
    void testAddSet() {
        assertEquals(workoutExerciseA, workoutExerciseC);
        workoutExerciseA.addSet(setF);
        assertNotEquals(workoutExerciseA, workoutExerciseC);
        workoutExerciseC.addSet(setF);
        assertEquals(workoutExerciseA, workoutExerciseC);
    }

    @Test
    void testToString() {
        assertEquals("barbell back squat\n" + setA.toString()
                + "\n" + setB.toString() + "\n", workoutExerciseB.toString());
    }

    @Test
    void testGetName() {
        assertEquals("bench press", workoutExerciseA.getName());
        assertEquals("barbell back squat", workoutExerciseB.getName());
    }

    private void instantiate() {
        workoutExerciseA = new WorkoutExercise("bench press");
        workoutExerciseB = new WorkoutExercise("barbell back squat");
        workoutExerciseC = new WorkoutExercise("bench press");
        workoutExerciseD = new WorkoutExercise("bench press");
        setA = new WorkoutSet(10, 100);
        setB = new WorkoutSet(8, 120);
        setC = new WorkoutSet(3, 190);
        setD = new WorkoutSet(7, 140);
        setE = new WorkoutSet(2, 200);
        setF = new WorkoutSet(3, 200);
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
}
