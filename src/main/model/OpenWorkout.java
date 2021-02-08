package model;


import ui.GymJournalApp;

import java.util.ArrayList;
import java.time.*;

// open workout for user to record stats
public class OpenWorkout implements Workout {
    private ArrayList<WorkoutExercise> workoutExercises;
    private LocalDate date;

    // EFFECTS: initializes arraylist, records date of workout
    public OpenWorkout() {
        workoutExercises = new ArrayList<WorkoutExercise>();
        this.date = LocalDate.now();
    }

    // MODIFIES: this
    // EFFECTS: adds exercise to workout exercise arraylist
    public void addExercise(WorkoutExercise workoutExercise) {
        this.workoutExercises.add(workoutExercise);
    }

    // EFFECTS: creates a reader friendly string of workout
    @Override
    public String toString() {
        String exerciseString = "";
        for (WorkoutExercise workoutExercise : workoutExercises) {
            exerciseString = exerciseString.concat(workoutExercise.toString() + "\n");
        }

        return (this.date + " workout.\n" + exerciseString);
    }

    // EFFECTS: calls exercise details in gymjournalapp
    @Override
    public void exerciseDetails(GymJournalApp gymJournalApp, String name, int num) {
        gymJournalApp.workoutExerciseDetails(this, name, num);
    }
}
