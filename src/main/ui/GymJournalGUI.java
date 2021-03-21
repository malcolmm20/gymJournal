package ui;

import ui.tools.*;

import javax.swing.*;
import java.util.HashSet;

public class GymJournalGUI extends JFrame {
    private MenuInterface menu;
    private AddWorkoutInterface addWorkout;
    private AddRoutineInterface addRoutine;
    private CheckPersonalBestsScreen checkPersonalBests;
    private CheckOneRepMaxesScreen checkOneRepMaxes;
    private ViewWorkoutHistoryScreen viewWorkoutHistory;
    private JPanel activeScreen;
    private HashSet<JPanel> setOfScreens;

    public GymJournalGUI() {
        super("Gym Journal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeScreens();
        activeScreen = menu;
    }

    private void initializeScreens() {
        menu = new MenuInterface();
        addWorkout = new AddWorkoutInterface();
        addRoutine = new AddRoutineInterface();
        // initialize and add to set of screens
        // when button is clicked make screen visible.
        // mehtod in menu to set active screen invisible and set all chosen screen to visible
        // return button
        // add image
    }

}
