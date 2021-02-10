package model;

import java.util.ArrayList;
import java.util.Objects;

// individual strength exercises, tracks name sets, reps, weight
public class WorkoutExercise {
    String name;
    private final ArrayList<WorkoutSet> sets;

    // EFFECTS: sets name to parameter, initializes array
    public WorkoutExercise(String name) {
        this.name = name;
        this.sets = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
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
            exerciseSet = exerciseSet.concat(set.toString() + "\n");
        }
        return (this.name + "\n" + exerciseSet);
    }
}
