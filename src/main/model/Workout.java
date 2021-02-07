package model;


import java.util.ArrayList;

// open workout for user to enter stats
public class Workout {
    private ArrayList<Exercise> exercises;

    public Workout() {
        exercises = new ArrayList<Exercise>();
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }
}
