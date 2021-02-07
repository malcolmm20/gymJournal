package model;

public class ExerciseSet {
    private int reps;
    private int weight;

    public ExerciseSet(int reps, int weight) {
        this.reps = reps;
        this.weight = weight;

    }

    @Override
    public String toString() {
        return (Integer.toString(this.reps) + " reps," + Integer.toString(this.weight) + "lbs");
    }
}
