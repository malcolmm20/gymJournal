package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoutineExerciseTest {
    private RoutineExercise exerciseA;
    private RoutineExercise exerciseB;
    private RoutineExercise exerciseC;
    private RoutineExercise exerciseD;
    private RoutineExercise exerciseE;

    @BeforeEach
    void setUp() {
        exerciseA = new RoutineExercise("bench press", 4);
        exerciseC = new RoutineExercise("bench press", 5);
        exerciseD = new RoutineExercise("leg press", 4);
        exerciseE = new RoutineExercise("bench press", 4);
    }

    @Test
    void testToJson() {
        assertTrue(exerciseA.toJson().similar(exerciseE.toJson()));
        assertFalse(exerciseA.toJson().similar(exerciseC.toJson()));
    }

    @Test
    void testEquals() {
        assertTrue(exerciseA.equals(exerciseE) && exerciseE.equals(exerciseA));
        assertTrue(exerciseA.equals(exerciseA));
        assertFalse(exerciseA.equals(exerciseB));
        assertFalse(exerciseA.equals(exerciseC) || exerciseC.equals(exerciseA));
        assertFalse(exerciseC.equals(exerciseD) || exerciseD.equals(exerciseC));
        assertFalse(exerciseA.equals(exerciseD) || exerciseD.equals(exerciseA));
    }

    @Test
    void testHashCode() {
        assertTrue(exerciseA.hashCode() == exerciseE.hashCode());
        assertFalse(exerciseA.hashCode() == exerciseC.hashCode());
    }

    @Test
    void testGetName() {
        assertEquals("bench press", exerciseA.getName());
        assertEquals("leg press", exerciseD.getName());
    }

    @Test
    void testGetSets() {
        assertEquals(4, exerciseA.getSets());
        assertEquals(5, exerciseC.getSets());
    }

    @Test
    void testToString() {
        assertEquals("bench press: 4 sets", exerciseA.toString());
        assertEquals("leg press: 4 sets", exerciseD.toString());
    }

}
