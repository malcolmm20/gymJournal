package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

// open workout for user to record stats
public class OpenWorkout {
    private final ArrayList<WorkoutExercise> workoutExercises;
    private final LocalDate date;

    // EFFECTS: initializes arraylist, records date of workout
    public OpenWorkout() {
        workoutExercises = new ArrayList<>();
        this.date = LocalDate.now();
    }

    // EFFECTS: returns date
    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OpenWorkout that = (OpenWorkout) o;
        return Objects.equals(workoutExercises, that.workoutExercises)
                && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workoutExercises, date);
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

    // EFFECTS: returns each exercise in workout's set with heaviest weight
    public HashMap<String, WorkoutSet> heaviestSets() {
        HashMap<String, WorkoutSet> heaviestSets = new HashMap<>();
        for (WorkoutExercise exercise : workoutExercises) {
            heaviestSets.put(exercise.getName(), exercise.heaviestSet());
        }
        return heaviestSets;
    }
}
