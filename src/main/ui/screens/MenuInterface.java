package ui.screens;

import model.GymJournal;
import model.OpenWorkout;
import model.Routine;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;
import ui.GymJournalGUI;
import ui.screens.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// class for gym journal graphical user interface
// modelled after simple drawing player gui
public class MenuInterface extends Screen {
    private static final String JSON_STORE = "./data/gymJournalLog.json";

    private JsonReader reader;
    private JsonWriter writer;


    public MenuInterface(GymJournalGUI gui, GymJournal gj) {
        super(gui, gj);
        addTitle();
        reader = new JsonReader(JSON_STORE);
        writer = new JsonWriter(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: adds welcome image and message to menu screen
    private void addTitle() {
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel();
        titlePanel.setSize(700, 700);
        titlePanel.setOpaque(false);
        title.setIcon(new ImageIcon("./data/barbell.png"));
        titlePanel.add(title);
        add(titlePanel, BorderLayout.CENTER);
    }

    // EFFECTS: instantiates buttons with actionlisteners
    @Override
    protected void createTools() {
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(0,1));
        toolArea.setSize(new Dimension(0, 0));
        add(toolArea, BorderLayout.SOUTH);
        addButtons(toolArea);
    }

    // MODIFIES: toolArea
    // EFFECTS: creates and adds buttons to area
    private void addButtons(JComponent toolArea) {
        JButton addRoutine = new JButton("Add Routine");
        addRoutine.addActionListener(e -> cl.show(gui,"routine"));
        toolArea.add(addRoutine);
        JButton addWorkout = new JButton("Add Workout");
        addWorkout.addActionListener(e -> cl.show(gui,"workout"));
        toolArea.add(addWorkout);
        JButton checkOneRepMaxes = new JButton("Check One Rep Maxes");
        checkOneRepMaxes.addActionListener(e -> cl.show(gui,"orm"));
        toolArea.add(checkOneRepMaxes);
        JButton checkPersonalBests = new JButton("Check Personal Bests");
        checkPersonalBests.addActionListener(e -> cl.show(gui,"pb"));
        toolArea.add(checkPersonalBests);
        JButton viewWorkoutHistory = new JButton("View Workout History");
        viewWorkoutHistory.addActionListener(e -> viewHistoryMethod());
        toolArea.add(viewWorkoutHistory);
        JButton saveJournal = new JButton("Save Gym Journal");
        saveJournal.addActionListener(e -> saveGymJournal());
        toolArea.add(saveJournal);
        JButton loadJournal = new JButton("Load Gym Journal");
        loadJournal.addActionListener(e -> loadGymJournal());
        toolArea.add(loadJournal);
    }

    private void viewHistoryMethod() {
        ((ViewWorkoutHistoryScreen)gui.getScreenMap().get("history")).updateList();
        cl.show(gui,"history");
    }


    // MODIFIES: this
    // EFFECTS: loads gym journal from file
    // CITATION: modelled after loadWorkRoom from JsonSerializationDemo.ui.WorkRoomApp
    public void loadGymJournal() {
        try {
            HashMap<String, ArrayList<Writable>> gjHashMap = reader.read();
            loadRoutines(gjHashMap.get("routines"));
            loadWorkouts(gjHashMap.get("workouts"));
            //System.out.println("Loaded Gym Journal from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file " + JSON_STORE);
        }
    }

    // MODIFIES: gymJournal
    // EFFECTS: loads workouts from arraylist to gym journal
    private void loadWorkouts(ArrayList<Writable> workouts) {
        for (Writable w : workouts) {
            OpenWorkout ow = (OpenWorkout) w;
            gj.addWorkout(ow);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads routines from array list to gym journal
    private void loadRoutines(ArrayList<Writable> routines) {
        for (Writable w : routines) {
            Routine r = (Routine) w;
            gj.addRoutine(r);
        }
    }

    // EFFECTS: saves gym journal to file
    // CITATION: modelled after loadWorkRoom from JsonSerializationDemo.ui.WorkRoomApp
    public void saveGymJournal() {
        try {
            writer.open();
            writer.write(gj);
            writer.close();
            //System.out.println("Saved Gym Journal to " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file " + JSON_STORE);
        }
    }
}
