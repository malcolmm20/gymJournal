package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

// open workout for user to record stats
public class OpenWorkout implements Writable {
    private final ArrayList<WorkoutExercise> workoutExercises;
    private LocalDate date;

    // EFFECTS: initializes arraylist, records date of workout
    public OpenWorkout() {
        workoutExercises = new ArrayList<>();
        this.date = LocalDate.now();
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("date", this.date);
        jsonObject.put("exercises", workoutExercisesToJson());
        return jsonObject;
    }

    // EFFECTS: returns exercise array list as json array
    private JSONArray workoutExercisesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (WorkoutExercise e : this.workoutExercises) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns date
    public LocalDate getDate() {
        return date;
    }

    // MODIFIES: this
    // EFFECTS: sets date to given date
    public void setDate(LocalDate date) {
        this.date = date;
    }

    // REQUIRES: objects to be of same class
    // EFFECTS: returns equal if object o holds the same values as this
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
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

    // MODIFIES: this, localdate
    // EFFECTS:
    public void addDays(long daysAdded) {
        this.date = date.plusDays(daysAdded);
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
