package model;

import java.util.ArrayList;

//routines that users repeat
public class Routine {
    private String name;
    private ArrayList<RoutineExercise> exercises;

    public Routine(String name) {
        this.name = name;
        exercises = new ArrayList<RoutineExercise>();
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
}
