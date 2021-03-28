package ui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import model.GymJournal;
import ui.screens.*;

// a JPanel that the GUI will operate in
public class GymJournalGUI extends JPanel {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 900;
    private HashMap<String, JPanel> screenMap;
    private JFrame display;
    private JPanel menu;
    private JPanel addFreeWorkout;
    private JPanel addRoutineWorkout;
    private JPanel selectRoutine;
    private JPanel addRoutine;
    private JPanel checkPersonalBests;
    private JPanel checkOneRepMaxes;
    private JPanel viewWorkoutHistory;
    private JPanel activeScreen;
    private GymJournal gj;

    public GymJournalGUI() {
        super();
        display = new JFrame("myGymJournal");
        gj = new GymJournal();
        screenMap = new HashMap<>();
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());
        initializeScreens();
        activeScreen = menu;
        initializeGraphics();
        addToHashMap();
    }

    // MODIFIES: this
    // EFFECTS: adds screens to hashmaps so they can be easily referenced
    private void addToHashMap() {
        screenMap.put("menu", menu);
        screenMap.put("free workout", addFreeWorkout);
        screenMap.put("routine workout", addRoutineWorkout);
        screenMap.put("select routine", selectRoutine);
        screenMap.put("routine", addRoutine);
        screenMap.put("pb", checkPersonalBests);
        screenMap.put("orm", checkOneRepMaxes);
        screenMap.put("history", viewWorkoutHistory);
    }

    // EFFECTS: returns screen hash map
    public HashMap<String, JPanel> getScreenMap() {
        return screenMap;
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
        addFreeWorkout = new AddFreeWorkoutInterface(this, gj);
        add(addFreeWorkout, "free workout");
        selectRoutine = new SelectRoutine(this, gj);
        add(selectRoutine, "select routine");
        addRoutineWorkout = new AddRoutineWorkoutInterface(this, gj);
        add(addRoutineWorkout, "routine workout");
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
