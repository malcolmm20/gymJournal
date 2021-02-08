package model;

import ui.GymJournalApp;

// interface for routine and open workout to reduce repetition in GymJournalApp
public interface Workout {
    void exerciseDetails(GymJournalApp gymJournalApp, String name, int num);
}
