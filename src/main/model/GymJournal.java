package model;


import java.util.ArrayList;
import java.util.HashMap;

//journal that records workouts, exercises
public class GymJournal {

    private ArrayList<OpenWorkout> workoutHistory;
    private HashMap<String, WorkoutExercise> exerciseHashMap;
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
    public void addWorkout(OpenWorkout openWorkout) {
        this.workoutHistory.add(openWorkout);
    }


    // MODIFIES: this
    // EFFECTS: adds routine to routineHashMap
    public void addRoutine(Routine routine) {
        this.routineHashMap.put(routine.getName(), routine);
    }

    // EFFECTS: returns routine corresponding to string key
    public Routine findRoutine(String string) {
        return routineHashMap.get(string);
    }

    // EFFECTS: returns string of all routines in routineHashMap
    public String routineString() {
        String result = "";
        for (HashMap.Entry<String, Routine> routine : routineHashMap.entrySet()) {
            result = result.concat(routine.getValue().toString() + "\n");
        }
        return result;
    }

    // EFFECTS: returns first num elements of workoutHistory as string
    //          if num > workoutHistory size, returns workoutHistory string
    public String workoutString(int num) {
        String result = "";
        if (num > workoutHistory.size()) {
            for (OpenWorkout workout : workoutHistory) {
                result = result.concat(workout.toString() + "\n");
            }
        } else {
            for (int i = 0; i < num; i++) {
                result = result.concat(workoutHistory.get(i).toString() + "\n");
            }
        }
        return result;
    }
}
