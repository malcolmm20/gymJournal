package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OpenWorkoutExerciseSetTest {
    private WorkoutExerciseSet setA;
    private WorkoutExerciseSet setB;

    @BeforeEach
    void setUp() {
        setA = new WorkoutExerciseSet(8, 150);
        setB = new WorkoutExerciseSet(12, 0);
    }

    @Test
    void toStringTest() {
        assertEquals("8 reps, 150 lbs", setA.toString());
        assertEquals("12 reps, 0 lbs", setB.toString());
    }
}
