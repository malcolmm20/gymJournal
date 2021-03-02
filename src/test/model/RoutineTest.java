package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RoutineTest {
    private ArrayList<RoutineExercise> exerciseArrayListA;
    private ArrayList<RoutineExercise> exerciseArrayListB;
    private ArrayList<RoutineExercise> exerciseArrayListC;
    private RoutineExercise exerciseA;
    private RoutineExercise exerciseB;
    private RoutineExercise exerciseC;
    private RoutineExercise exerciseD;
    private Routine routineA;
    private Routine routineB;
    private Routine routineC;
    private Routine routineD;


    @BeforeEach
    void setUp() {
        instantiate();
        addExercises();
    }

    @Test
    void testToJson() {
        assertTrue(routineA.toJson().similar(routineB.toJson()));
        assertFalse(routineA.toJson().similar(routineC.toJson()));
    }

    @Test
    void testEquals() {
        assertEquals(routineA, routineA);
        assertEquals(routineA, routineB);
        assertEquals(routineB, routineA);
        assertNotEquals(routineA, routineD);
        assertFalse(routineA.equals(routineC) || routineC.equals(routineA));

        routineB.addExercise(exerciseA);
        assertFalse(routineA.equals(routineB) || routineB.equals(routineA));
    }

    @Test
    void testHashCode() {
        assertEquals(routineA.hashCode(), routineB.hashCode());
        assertNotEquals(routineA.hashCode(), routineC.hashCode());
    }

    @Test
    void testGetName() {
        assertEquals("chest day", routineA.getName());
        assertEquals("leg day", routineC.getName());
    }

    @Test
    void testGetExercises() {
        exerciseArrayListA.add(exerciseA);
        exerciseArrayListA.add(exerciseB);
        exerciseArrayListB.add(exerciseC);
        exerciseArrayListB.add(exerciseD);
        exerciseArrayListC.add(exerciseD);
        exerciseArrayListC.add(exerciseC);

        assertEquals(exerciseArrayListA, routineA.getExercises());
        assertEquals(exerciseArrayListB, routineC.getExercises());
        assertNotEquals(exerciseArrayListC, routineC.getExercises());
    }

    @Test
    void testToString() {
        assertEquals(routineA.getName().concat("\n" + exerciseA.toString()
                + "\n" + exerciseB.toString() + "\n"), routineA.toString());
        assertEquals(routineC.getName().concat("\n" + exerciseC.toString()
                + "\n" + exerciseD.toString() + "\n"), routineC.toString());
    }

    // MODIFIES: this
    // EFFECTS: instantiates fields
    private void instantiate() {
        exerciseArrayListA = new ArrayList<>();
        exerciseArrayListB = new ArrayList<>();
        exerciseArrayListC = new ArrayList<>();
        routineA = new Routine("chest day");
        routineB = new Routine("chest day");
        routineC = new Routine("leg day");
        exerciseA = new RoutineExercise("bench press", 4);
        exerciseB = new RoutineExercise("incline bench press", 4);
        exerciseC = new RoutineExercise("squats", 5);
        exerciseD = new RoutineExercise("leg press", 4);
    }

    // MODIFIES: routineA, routineB, routine C
    // EFFECTS: adds exercises to routineA/B/C
    private void addExercises() {
        routineA.addExercise(exerciseA);
        routineA.addExercise(exerciseB);
        routineB.addExercise(exerciseA);
        routineB.addExercise(exerciseB);
        routineC.addExercise(exerciseC);
        routineC.addExercise(exerciseD);
    }
}
