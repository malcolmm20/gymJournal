package ui;

import javax.swing.*;
import java.util.HashMap;

import model.GymJournal;
import ui.screens.*;

public class GymJournalGUI extends JFrame {
    private MenuInterface menu;
    private AddWorkoutInterface addWorkout;
    private AddRoutineInterface addRoutine;
    private CheckPersonalBestsScreen checkPersonalBests;
    private CheckOneRepMaxesScreen checkOneRepMaxes;
    private ViewWorkoutHistoryScreen viewWorkoutHistory;
    private JPanel activeScreen;
    private HashMap<String, JPanel> screens;
    private GymJournal gj;

    public GymJournalGUI() {
        super("Gym Journal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeScreens();
        activeScreen = menu;
        gj = new GymJournal();
    }

    // MODIFIES: screens hashmap, screens being initialized
    // EFFECTS: initializes screens, adds them to screens hashmap
    private void initializeScreens() {
        menu = new MenuInterface(this, gj);
        screens.put("menu", menu);
        addWorkout = new AddWorkoutInterface(this, gj);
        screens.put("workout", addWorkout);
        addRoutine = new AddRoutineInterface(this, gj);
        screens.put("routine", addRoutine);
        checkPersonalBests = new CheckPersonalBestsScreen(this, gj);
        screens.put("pb", checkPersonalBests);
        checkOneRepMaxes = new CheckOneRepMaxesScreen(this, gj);
        screens.put("orm", checkOneRepMaxes);
        viewWorkoutHistory = new ViewWorkoutHistoryScreen(this, gj);
        screens.put("history", viewWorkoutHistory);
    }

    // MODIFIES: activescreen, screenKey screen
    // EFFECTS: sets active screen to invisible, makes screen corresponding to screenkey
    // the active screen, sets it to visible
    public void showScreen(String screenKey) {
        activeScreen.setVisible(false);
        activeScreen = screens.get(screenKey);
        activeScreen.setVisible(true);
    }

}
