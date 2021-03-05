package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkoutSetTest {
    private WorkoutSet setA;
    private WorkoutSet setB;
    private WorkoutSet setC;
    private WorkoutSet setD;
    private WorkoutSet setE;
    private WorkoutSet setF;

    @BeforeEach
    void setUp() {
        setA = new WorkoutSet(8, 150);
        setB = new WorkoutSet(12, 0);
        setC = new WorkoutSet(8, 150);
        setE = new WorkoutSet(1, 200);
        setF = new WorkoutSet(8, 175);
    }

    @Test
    void testToJson() {
        JSONObject jsonA = new JSONObject();
        JSONObject jsonB = new JSONObject();
        initializeJson(jsonA, jsonB);
        assertTrue(setA.toJson().similar(jsonA));
        assertFalse(setA.toJson().similar(jsonB));
    }

    private void initializeJson(JSONObject jsonA, JSONObject jsonB) {
        jsonA.put("weight", 150);
        jsonA.put("reps", 8);
        jsonB.put("weight", 200);
        jsonB.put("reps", 10);
    }

    @Test
    void testToString() {
        assertEquals("8 reps, 150 lbs", setA.toString());
        assertEquals("12 reps, 0 lbs", setB.toString());
    }

    @Test
    void testEquals() {
        assertEquals(setA, setA);
        assertEquals(setA, setC);
        assertNotEquals(setA, setD);
        assertNotEquals(setA, setB);
        assertNotEquals(setA, setF);
    }

    @Test
    void testHashCode() {
        assertEquals(setA.hashCode(), setA.hashCode());
        assertEquals(setA.hashCode(), setC.hashCode());
        assertNotEquals(setA.hashCode(), setB.hashCode());
    }

    @Test
    void testGetReps() {
        assertEquals(8, setA.getReps());
        assertEquals(12, setB.getReps());
    }

    @Test
    void testGetWeight() {
        assertEquals(150, setA.getWeight());
        assertEquals(0, setB.getWeight());
    }

    @Test
    void testCalculateOneRepMax() {
        assertEquals(190, setA.calculateOneRepMax());
        assertEquals(0, setB.calculateOneRepMax());
        assertEquals(200, setE.calculateOneRepMax());
    }

}
