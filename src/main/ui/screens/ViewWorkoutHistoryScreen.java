package ui.screens;

import model.GymJournal;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.GymJournalGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewWorkoutHistoryScreen extends Screen {

    public ViewWorkoutHistoryScreen(GymJournalGUI gui, GymJournal gj) {
        super(gui, gj);
    }

}
