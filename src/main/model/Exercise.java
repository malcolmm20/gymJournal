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

    public String getName() {
        return this.name;
    }

    public int getPersonalBest() {
        return this.personalBest;
    }

    public int getOneRepMax() {
        return this.oneRepMax;
    }
}
