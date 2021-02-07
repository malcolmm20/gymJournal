package model;

import java.util.ArrayList;

// individual strength exercises
public class Exercise {

    String name;
    private ArrayList<ExerciseSet> sets;
    int personalBest;
    int oneRepMax;

    public Exercise(String name) {
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
