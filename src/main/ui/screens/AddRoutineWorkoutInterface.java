package ui.screens;

import model.GymJournal;
import model.Routine;
import ui.GymJournalGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// screen where users can add a workout following an existing routine
public class AddRoutineWorkoutInterface extends InputInterface {

    private ArrayList<JTextField> fields;

    public AddRoutineWorkoutInterface(Routine routine, GymJournalGUI gui, GymJournal gj) {
        super(gui, gj);
    }
}
