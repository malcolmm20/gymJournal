package model;


import java.util.ArrayList;
import java.util.HashMap;

//journal that records workouts, exercises
public class GymJournal {

    private ArrayList<OpenWorkout> openWorkoutHistory;
    private HashMap<String, WorkoutExercise> exerciseHashMap;
    private HashMap<String, Routine> routineHashMap;

    // MODIFIES: this
    // EFFECTS: initializes hashmaps and arraylist
    public GymJournal() {
        openWorkoutHistory = new ArrayList<>();
        exerciseHashMap = new HashMap<>();
        routineHashMap = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: adds workout to workout history
    public void addWorkout(OpenWorkout openWorkout) {
        this.openWorkoutHistory.add(openWorkout);
    }


    // MODIFIES: this
    // EFFECTS: adds routine to routineHashMap
    public void addRoutine(Routine routine) {
        this.routineHashMap.put(routine.getName(), routine);
    }

    public Routine findRoutine(String string) {
        return routineHashMap.get(string);
    }
}
