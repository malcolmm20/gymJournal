package model;


import java.util.ArrayList;
import java.time.*;

// open workout for user to enter stats
public class Workout {
    private ArrayList<Exercise> exercises;
    private LocalDate date;

    public Workout() {
        exercises = new ArrayList<Exercise>();
        this.date = LocalDate.now();
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    @Override
    public String toString() {
        String exerciseString = "";
        for (Exercise exercise : exercises) {
            exerciseString = exerciseString.concat(exercise.toString() + "\n");
        }

        return (this.date + " workout.\n" + exerciseString);
    }
}
