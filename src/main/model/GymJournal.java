package model;


import java.util.ArrayList;
import java.util.HashMap;

//journal that records workouts, sets reps weights for exercises
public class GymJournal {

    private ArrayList<Workout> workoutHistory;
    private HashMap<String, Exercise> exerciseHashMap;
    private HashMap<String, Routine> routineHashMap;

    public GymJournal() {
        workoutHistory = new ArrayList<Workout>();
        exerciseHashMap = new HashMap<>();
        routineHashMap = new HashMap<>();
    }

    public void addWorkout(Workout workout) {
        this.workoutHistory.add(workout);
        updateExerciseHashMap();
    }

    private void updateExerciseHashMap() {
    }

}
