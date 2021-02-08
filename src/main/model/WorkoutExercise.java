package model;

import java.util.ArrayList;

// individual strength exercises, tracks name sets, reps, weight
public class WorkoutExercise {
    String name;
    private ArrayList<WorkoutExerciseSet> sets;

    // EFFECTS: sets name to parameter, initializes array
    public WorkoutExercise(String name) {
        this.name = name;
        this.sets = new ArrayList<WorkoutExerciseSet>();
    }

    // MODIFIES: this
    // EFFECTS: adds exercise to workoutexercise arraylist
    public void addSet(WorkoutExerciseSet set) {
        this.sets.add(set);
    }

    // EFFECTS: creates a reader friendly string of exercise
    @Override
    public String toString() {
        String exerciseSet = "";
        for (WorkoutExerciseSet set : sets) {
            exerciseSet = exerciseSet.concat(set.toString() + "\n");
        }
        return (this.name + "\n" + exerciseSet);
    }
}
