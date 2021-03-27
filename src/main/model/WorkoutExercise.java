package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Objects;

// individual strength exercises, tracks name sets, reps, weight
public class WorkoutExercise implements Writable {
    String name;
    private final ArrayList<WorkoutSet> sets;

    // EFFECTS: sets name to parameter, initializes array
    public WorkoutExercise(String name) {
        this.name = name;
        this.sets = new ArrayList<>();
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("exercise name", this.name);
        jsonObject.put("sets", workoutSetsToJson());
        return jsonObject;
    }

    // EFFECTS: returns sets array as JSON array
    private JSONArray workoutSetsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (WorkoutSet set : this.sets) {
            jsonArray.put(set.toJson());
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
        WorkoutExercise that = (WorkoutExercise) o;
        return Objects.equals(name, that.name)
                && Objects.equals(sets, that.sets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sets);
    }

    // EFFECTS: returns exercise name
    public String getName() {
        return name;
    }

    // EFFECTS: returns set done with heaviest weight.
    // if there are two with same weight, returns the set with most reps
    public WorkoutSet heaviestSet() {
        WorkoutSet heaviestSet = new WorkoutSet(0, 0);
        for (WorkoutSet set : sets) {
            if (set.getWeight() > heaviestSet.getWeight()) {
                heaviestSet = set;
            } else if (set.getWeight() == heaviestSet.getWeight()) {
                if (set.getReps() > heaviestSet.getReps()) {
                    heaviestSet = set;
                }
            }
        }
        return heaviestSet;
    }

    // MODIFIES: this
    // EFFECTS: adds exercise to workoutExercise arraylist
    public void addSet(WorkoutSet set) {
        this.sets.add(set);
    }

    // EFFECTS: creates a reader friendly string of exercise
    @Override
    public String toString() {
        String exerciseSet = "";
        for (WorkoutSet set : sets) {
            exerciseSet = exerciseSet.concat(set.toString() + ".\n");
        }
        return (this.name + ".\n" + exerciseSet);
    }
}
