package model;

import ui.GymJournalApp;

import java.util.ArrayList;

//routines that users repeat
public class Routine implements Workout {
    private final String name;
    private ArrayList<RoutineExercise> exercises;

    public Routine(String name) {
        this.name = name;
        exercises = new ArrayList<>();
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

    public void callExerciseDetails(GymJournalApp gymJournalApp, String name, int num) {
        gymJournalApp.routineExerciseDetails(this, name, num);
    }

    // EFFECTS: returns list of exercises in routine
    public ArrayList<RoutineExercise> getExercises() {
        return exercises;
    }
}
