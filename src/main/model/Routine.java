package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Objects;

//routines that users repeat
public class Routine implements Writable {
    private final String name;
    private final ArrayList<RoutineExercise> exercises;

    public Routine(String name) {
        this.name = name;
        exercises = new ArrayList<>();
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", this.name);
        jsonObject.put("exercises", exercisesToJson());
        return jsonObject;
    }

    // EFFECTS: returns exercise array list as json array
    private JSONArray exercisesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (RoutineExercise e : this.exercises) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

    // REQUIRES: objects to be of same class
    // EFFECTS: returns equal if object o holds the same values as this
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        Routine routine = (Routine) o;
        return Objects.equals(name, routine.name)
                && Objects.equals(exercises, routine.exercises);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, exercises);
    }

    // MODIFIES: this
    // EFFECTS: adds exercise to exercises arraylist
    public void addExercise(RoutineExercise exercise) {
        this.exercises.add(exercise);
    }

    // EFFECTS: returns routine name
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns list of exercises in routine
    public ArrayList<RoutineExercise> getExercises() {
        return exercises;
    }

    // EFFECTS: creates a reader friendly string of routine
    @Override
    public String toString() {
        String result = "";
        for (RoutineExercise exercise : exercises) {
            result = result.concat(exercise.toString() + ".\n");
        }
        return (this.name.concat(".\n" + result));
    }
}
