package model;

import org.json.JSONObject;
import persistence.Writable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

// each instance is a set. records reps and weight, for a set.
public class WorkoutSet implements Writable {
    private final int reps;
    private final int weight;

    // REQUIRES: reps are positive, weight is non-negative
    // EFFECTS: sets reps and weight equal to parameter values
    public WorkoutSet(int reps, int weight) {
        this.reps = reps;
        this.weight = weight;

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
        WorkoutSet that = (WorkoutSet) o;
        return reps == that.reps
                && weight == that.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reps, weight);
    }

    // EFFECTS: creates a reader friendly string of set
    @Override
    public String toString() {
        return (this.reps + " reps, " + this.weight + " lbs");
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
        if (this.reps == 1) {
            oneRepMax = weight;
        } else {
            oneRepMax = weight * (1 + (reps / 30.0));
        }
        BigDecimal bd = new BigDecimal(oneRepMax).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reps", this.reps);
        jsonObject.put("weight", this.weight);
        return jsonObject;
    }
}
