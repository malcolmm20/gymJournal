package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

// each instance is a set. records reps and weight, for a set.
public class WorkoutExerciseSet {
    private int reps;
    private int weight;

    // REQUIRES: reps are positive, weight is non-negative
    // EFFECTS: sets reps and weight equal to parameter values
    public WorkoutExerciseSet(int reps, int weight) {
        this.reps = reps;
        this.weight = weight;

    }

    // EFFECTS: creates a reader friendly string of set
    @Override
    public String toString() {
        return (Integer.toString(this.reps) + " reps, " + Integer.toString(this.weight) + " lbs");
    }

    // EFFECTS: returns reps
    public int getReps() {
        return this.reps;
    }

    // EFFECTS: returns weight
    public int getWeight() {
        return this.weight;
    }

    // EFFECTS: calculates max weight exercise could be performed for one repetition
    public double calculateOneRepMax() {
        double oneRepMax;
        double reps = this.reps;
        double weight = this.weight;
        if (this.reps > 1) {
            oneRepMax = weight * (1 + (reps / 30.0));
        } else {
            oneRepMax = weight;
        }
        BigDecimal bd = new BigDecimal(oneRepMax).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
