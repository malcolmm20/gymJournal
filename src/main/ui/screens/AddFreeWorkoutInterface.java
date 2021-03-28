package ui.screens;

import model.GymJournal;
import ui.GymJournalGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// screen where user can add new free workouts
public class AddFreeWorkoutInterface extends Screen {

    private ArrayList<JTextField> fields;

    public AddFreeWorkoutInterface(GymJournalGUI gui, GymJournal gj) {
        super(gui, gj);
    }


}
