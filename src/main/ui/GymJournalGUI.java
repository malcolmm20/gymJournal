package ui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import model.GymJournal;
import ui.screens.*;

public class GymJournalGUI extends JPanel {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 900;
    private JFrame display;
    private JPanel menu;
    private JPanel addWorkout;
    private JPanel addRoutine;
    private JPanel checkPersonalBests;
    private JPanel checkOneRepMaxes;
    private JPanel viewWorkoutHistory;
    private JPanel activeScreen;
    private HashMap<String, JPanel> screens;
    private GymJournal gj;

    public GymJournalGUI() {
        super();
        display = new JFrame("myGymJournal");
        gj = new GymJournal();
        screens = new HashMap<>();
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());
        initializeScreens();
        activeScreen = menu;
        initializeGraphics();
    }


    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where the GymJournal will operate
    private void initializeGraphics() {
        display.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        display.setLocationRelativeTo(null);
        display.getContentPane().setBackground(Color.WHITE);
        display.add(this);
        display.setVisible(true);
    }

    // MODIFIES: screens hashmap, screens being initialized
    // EFFECTS: initializes screens, adds them to screens hashmap
    private void initializeScreens() {
        menu = new MenuInterface(this, gj);
        add(menu, "menu");
        addWorkout = new AddWorkoutInterface(this, gj);
        add(addWorkout, "workout");
        addRoutine = new AddRoutineInterface(this, gj);
        add(addRoutine, "routine");
        checkPersonalBests = new CheckPersonalBestsScreen(this, gj);
        add(checkPersonalBests, "pb");
        checkOneRepMaxes = new CheckOneRepMaxesScreen(this, gj);
        add(checkOneRepMaxes, "orm");
        viewWorkoutHistory = new ViewWorkoutHistoryScreen(this, gj);
        add(viewWorkoutHistory, "history");
    }

}
