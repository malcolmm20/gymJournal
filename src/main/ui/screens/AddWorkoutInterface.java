package ui.screens;

import model.GymJournal;
import ui.GymJournalGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// screen where user can add new workouts
public class AddWorkoutInterface extends Screen {

    public AddWorkoutInterface(GymJournalGUI gui, GymJournal gj) {
        super(gui, gj);
    }
}
