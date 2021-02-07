package model;

// each instance is a set. records reps and weight, for a set.
public class ExerciseSet {
    private int reps;
    private int weight;

    // REQUIRES: reps are positive, weight is non-negative
    // EFFECTS: sets reps and weight equal to parameter values
    public ExerciseSet(int reps, int weight) {
        this.reps = reps;
        this.weight = weight;

    }

    @Override
    public String toString() {
        return (Integer.toString(this.reps) + " reps, " + Integer.toString(this.weight) + " lbs");
    }
}
