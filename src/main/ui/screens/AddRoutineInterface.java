package ui.screens;

import model.GymJournal;
import model.Routine;
import model.RoutineExercise;
import ui.GymJournalGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// screen where user can add routines
public class AddRoutineInterface extends InputInterface {

    private JOptionPane popUp;
    private JButton enter;
    private JButton clear;
    private ArrayList<JTextField> fields;
    private int numExercises;

    public AddRoutineInterface(GymJournalGUI gui, GymJournal gj) {
        super(gui, gj);
        popUp = new JOptionPane();
    }

    // MODIFIES: this
    // EFFECTS: shows pop up that prompts user to enter number of exercises
    public void showPopUp() {
        fields = new ArrayList<>();
        String input = popUp.showInputDialog("Enter how many exercises your routine will include: ");
        try {
            numExercises = Integer.parseInt(input);
            makeForm();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Your input was not an integer. Please enter an integer");
            showPopUp();
        }
    }

    // MODIFIES: this
    // EFFECTS: constructs form for user entry
    private void makeForm() {
        northPanel.setLayout(new GridLayout((numExercises + 1) * 2, 2));
        JLabel routineName = new JLabel("Enter your routine name:");
        JTextField routineNameField = new JTextField();
        fields.add(routineNameField);
        northPanel.add(routineName);
        northPanel.add(routineNameField);
        for (int i = 0; i < numExercises; i++) {
            exerciseEntries(i);
        }
        addButtons();
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS: adds buttons for entering data and clearing entry
    private void addButtons() {
        enter = new JButton("Enter");
        clear = new JButton("Clear");
        enter.addActionListener(e -> processEntry());
        clear.addActionListener(e -> clearSelection());
        northPanel.add(enter);
        northPanel.add(clear);
    }

    // EFFECTS: clears text fields
    private void clearSelection() {
        for (JTextField field : fields) {
            field.setText("");
        }
        revalidate();
    }

    // EFFECTS: processes textfield data
    private void processEntry() {
        Routine routine = new Routine(fields.get(0).getText());
        for (int i = 1; i <= numExercises * 2; i = i + 2) {
            RoutineExercise exercise = new RoutineExercise(fields.get(i).getText(),
                    Integer.parseInt(fields.get(i + 1).getText()));
            routine.addExercise(exercise);
        }
        gj.addRoutine(routine);
        clearSelection();
    }

    // MODIFIES: this
    // EFFECTS: constructs field for amount of exercises needed
    private void exerciseEntries(int i) {
        JLabel exerciseName = new JLabel("Exercise " + (i + 1) +  " name: ");
        JTextField exerciseNameField = new JTextField();
        fields.add(exerciseNameField);
        northPanel.add(exerciseName);
        northPanel.add(exerciseNameField);
        JLabel numSets = new JLabel("How many sets will be performed? ");
        JTextField numSetsField = new JTextField();
        fields.add(numSetsField);
        northPanel.add(numSets);
        northPanel.add(numSetsField);
    }
}
