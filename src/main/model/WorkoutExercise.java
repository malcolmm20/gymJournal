package model;

import ui.GymJournalApp;

import java.util.ArrayList;

// individual strength exercises, tracks name sets, reps, weight
public class WorkoutExercise implements Exercise {

    String name;
    private ArrayList<ExerciseSet> sets;
    int personalBest;
    int oneRepMax;

    public WorkoutExercise(String name) {
        this.name = name;
        this.sets = new ArrayList<ExerciseSet>();
    }

    public void addSet(ExerciseSet set) {
        this.sets.add(set);
    }

    @Override
    public String toString() {
        String exerciseSet = "";
        for (ExerciseSet set : sets) {
            exerciseSet = exerciseSet.concat(set.toString() + "\n");
        }
        return (this.name + "\n" + exerciseSet);
    }
}
