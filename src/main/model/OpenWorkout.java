package model;


import ui.GymJournalApp;

import java.util.ArrayList;
import java.time.*;

// open workout for user to enter stats
public class OpenWorkout implements Workout {
    private ArrayList<WorkoutExercise> workoutExercises;
    private LocalDate date;

    public OpenWorkout() {
        workoutExercises = new ArrayList<WorkoutExercise>();
        this.date = LocalDate.now();
    }

    public void addExercise(WorkoutExercise workoutExercise) {
        this.workoutExercises.add(workoutExercise);
    }

    @Override
    public String toString() {
        String exerciseString = "";
        for (WorkoutExercise workoutExercise : workoutExercises) {
            exerciseString = exerciseString.concat(workoutExercise.toString() + "\n");
        }

        return (this.date + " workout.\n" + exerciseString);
    }

    @Override
    public void callExerciseDetails(GymJournalApp gymJournalApp, String name, int num) {
        gymJournalApp.workoutExerciseDetails(this, name, num);
    }
}
