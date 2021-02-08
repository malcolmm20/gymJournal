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

    // EFFECTS: calls exerciseDetails in gymJournalApp
    public void exerciseDetails(GymJournalApp gymJournalApp, String name, int num) {
        RoutineExercise exercise = new RoutineExercise(name, num);
        this.addExercise(exercise);

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
            result = result.concat(exercise.toString() + "\n");
        }
        return (this.name.concat("\n" + result));
    }
}
