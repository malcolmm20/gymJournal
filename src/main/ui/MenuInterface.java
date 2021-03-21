package ui;

import model.GymJournal;
import model.OpenWorkout;
import model.Routine;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;
import ui.tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// class for gym journal graphical user interface
// modelled after simple drawing player gui
public class MenuInterface extends JPanel {

    private static final String JSON_STORE = "./data/gymJournalLog.json";
    public static final int WIDTH = 700;
    public static final int HEIGHT = 900;

    private JsonReader reader;
    private JsonWriter writer;

    private GymJournal gj;

    public MenuInterface() {
        super();
        gj = new GymJournal();
        initializeGraphics();
    }

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

    private void createTools() {
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(0,1));
        toolArea.setSize(new Dimension(0, 0));
        add(toolArea, BorderLayout.SOUTH);

        JButton addRoutine = new JButton("Add Routine");
        toolArea.add(addRoutine);
        AddWorkoutTool addWorkoutTool = new AddWorkoutTool(this, toolArea);
        CheckOneRepMaxTool checkOneRepMaxTool = new CheckOneRepMaxTool(this, toolArea);
        CheckPersonalBestsTool checkPersonalBestsTool = new CheckPersonalBestsTool(this, toolArea);
        LoadJournalTool loadJournalTool = new LoadJournalTool(this, toolArea);
        SaveJournalTool saveJournalTool = new SaveJournalTool(this, toolArea);
        ViewWorkoutHistoryTool viewWorkoutHistoryTool = new ViewWorkoutHistoryTool(this, toolArea);
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
