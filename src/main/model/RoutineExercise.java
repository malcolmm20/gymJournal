package model;

import java.util.Objects;

// stores name of exercise and amount of sets for each exercise in a routine
public class RoutineExercise {
    private final String name;
    private final int sets;

    // REQUIRES: sets to be a positive integer, name cannot be the empty string
    // EFFECTS: sets name, sets equal to parameter values
    public RoutineExercise(String name, int sets) {
        this.name = name;
        this.sets = sets;
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
        RoutineExercise that = (RoutineExercise) o;
        return sets == that.sets
                && Objects.equals(name, that.name);
    }

    // EFFECTS: returns hashcode
    @Override
    public int hashCode() {
        return Objects.hash(name, sets);
    }

    // REQUIRES: this to not be null
    // EFFECTS: returns exercise name
    public String getName() {
        return name;
    }

    // REQUIRES: this to not be null
    // EFFECTS: returns expected number of sets
    public int getSets() {
        return sets;
    }

    // EFFECTS: creates a reader friendly string of exercise
    @Override
    public String toString() {
        return (this.name + ": " + this.sets + " sets");
    }

}
