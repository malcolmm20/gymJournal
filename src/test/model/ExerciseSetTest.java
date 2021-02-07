package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseSetTest {
    private ExerciseSet setA;
    private ExerciseSet setB;

    @BeforeEach
    void setUp() {
        setA = new ExerciseSet(8, 150);
        setB = new ExerciseSet(12, 0);
    }

    @Test
    void toStringTest() {
        assertEquals("8 reps, 150 lbs", setA.toString());
        assertEquals("12 reps, 0 lbs", setB.toString());
    }
}
