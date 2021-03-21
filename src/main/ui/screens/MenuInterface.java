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
public class MenuInterface extends JPanel {
    private GymJournalGUI gui;
    private GymJournal gj;
    private static final String JSON_STORE = "./data/gymJournalLog.json";
    public static final int WIDTH = 700;
    public static final int HEIGHT = 900;

    private JsonReader reader;
    private JsonWriter writer;


    public MenuInterface(GymJournalGUI gui, GymJournal gj) {
        super();
        this.gui = gui;
        this.gj = gj;
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: adds welcome image and message to menu screen
    private void addTitle() {
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel();
        titlePanel.setSize(200, 80);
        titlePanel.setOpaque(false);
        titlePanel.setLocation(50,50);
        title.setIcon(new ImageIcon("./data/barbell.png"));
        titlePanel.add(title);
        add(titlePanel);
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where the GymJournal will operate
    private void initializeGraphics() {
        addTitle();
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createTools();
        setVisible(true);
    }

    // EFFECTS: instantiates buttons with actionlisteners
    private void createTools() {
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(0,1));
        toolArea.setSize(new Dimension(0, 0));
        add(toolArea, BorderLayout.SOUTH);

        JButton addRoutine = new JButton("Add Routine");
        addRoutine.addActionListener(e -> gui.showScreen("routine"));
        JButton addWorkout = new JButton("Add Workout");
        addWorkout.addActionListener(e -> gui.showScreen("workout"));
        JButton checkOneRepMaxes = new JButton("Check One Rep Maxes");
        checkOneRepMaxes.addActionListener(e -> gui.showScreen("orm"));
        JButton checkPersonalBests = new JButton("Check Personal Bests");
        checkPersonalBests.addActionListener(e -> gui.showScreen("pb"));
        JButton viewWorkoutHistory = new JButton("View Workout History");
        viewWorkoutHistory.addActionListener(e -> gui.showScreen("history"));
        JButton saveJournal = new JButton("Save Gym Journal");
        saveJournal.addActionListener(e -> saveGymJournal());
        JButton loadJournal = new JButton("Load Gym Journal");
        loadJournal.addActionListener(e -> loadGymJournal());
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
