package model;

import ui.GymJournalApp;

import java.lang.management.GarbageCollectorMXBean;

// stores name of exercise and amount of sets for each exercise in a routine
public class RoutineExercise implements Exercise {
    private final String name;
    private final int sets;

    // REQUIRES: sets to be a positive integer, name cannot be the empty string
    // EFFECTS: sets name, sets equal to parameter values
    public RoutineExercise(String name, int sets) {
        this.name = name;
        this.sets = sets;
    }

    // EFFECTS: returns exercise name
    public String getName() {
        return name;
    }

    // EFFECTS: returns expected number of sets
    public int getSets() {
        return sets;
    }
}
