package model;


import java.util.ArrayList;
import java.util.HashMap;

//journal that records workouts, sets reps weights for exercises
public class GymJournal {

    private ArrayList<Workout> workoutHistory;
    private HashMap<String, Exercise> exerciseHashMap;
    private HashMap<String, Routine> routineHashMap;

    // MODIFIES: this
    // EFFECTS: initializes hashmaps and arraylist
    public GymJournal() {
        workoutHistory = new ArrayList<>();
        exerciseHashMap = new HashMap<>();
        routineHashMap = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: adds workout to workout history
    public void addWorkout(Workout workout) {
        this.workoutHistory.add(workout);
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
