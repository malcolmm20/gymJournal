package persistence;

import model.GymJournal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

// modelled after JsonWriterTest from JsonSerializationDemo
public class JsonWriterTest {
    private GymJournal gymJournal;

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
    void testWriterEmptyWorkroom() {
        try {
            GymJournal gymJournal = new GymJournal();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(gymJournal);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            HashMap<String, ArrayList<Writable>> gjHashMap = reader.read();
            assertEquals(0, gjHashMap.get("workouts"));
            assertEquals(0, gjHashMap.get("routines"));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
