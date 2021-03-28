package ui.screens;

import model.GymJournal;
import model.Routine;
import model.RoutineExercise;
import ui.GymJournalGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// screen where user can add new free workouts
public class AddFreeWorkoutInterface extends InputInterface {

    private JButton enter;
    private JButton clear;
    private JPanel scrollPanel;
    private JOptionPane popUp;
    private ArrayList<JTextField> fields;
    private int numExercises;

    public AddFreeWorkoutInterface(GymJournalGUI gui, GymJournal gj) {
        super(gui, gj);
        popUp = new JOptionPane();
        scrollPanel = new JPanel();
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
        scrollPanel.removeAll();
        northPanel.removeAll();
        scrollPanel.revalidate();
        northPanel.revalidate();
        scrollPanel.setLayout(new GridLayout(numExercises * 2 + 1, 2));
        for (int i = 0; i < numExercises; i++) {
            exerciseEntries(i);
        }
        addButtons();
        makeScrollPane();
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS: makes scroll pane for JList
    private void makeScrollPane() {
        JScrollPane listScroller = new JScrollPane(scrollPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        listScroller.setPreferredSize(new Dimension(WIDTH, HEIGHT - 50));
        scrollPanel.setPreferredSize(new Dimension(scrollPanel.getWidth(), 1000));
        listScroller.setPreferredSize(new Dimension(700, 800));
        northPanel.add(listScroller);
    }

    // MODIFIES: this
    // EFFECTS: adds buttons for entering data and clearing entry
    private void addButtons() {
        enter = new JButton("Enter");
        clear = new JButton("Clear");
        enter.addActionListener(e -> processEntry());
        clear.addActionListener(e -> clearSelection());
        scrollPanel.add(enter);
        scrollPanel.add(clear);
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
        if (testEmpty()) {
            JOptionPane.showMessageDialog(this, "There cannot be any empty fields");
        } else if (!testNonIntegers()) {
            JOptionPane.showMessageDialog(this, "There are non-integers in integer fields");
        } else {
            Routine routine = new Routine(fields.get(0).getText());
            for (int i = 0; i < numExercises * 2; i += 2) {
                RoutineExercise exercise = new RoutineExercise(fields.get(i).getText(),
                        Integer.parseInt(fields.get(i + 1).getText()));
                routine.addExercise(exercise);
            }
            clearSelection();
            ((AddRoutineWorkoutInterface)gui.getScreenMap().get("routine workout")).updateForm(routine);
            cl.show(gui,"routine workout");
        }
    }

    // EFFECTS: returns false if a non integer is in an integer field
    private boolean testNonIntegers() {
        try {
            for (int i = 1; i < numExercises * 2; i = i + 2) {
                Integer.parseInt(fields.get(i).getText());
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    // EFFECTS: returns false if any fields are empty
    private boolean testEmpty() {
        for (JTextField field : this.fields) {
            if (field.getText().equals("")) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: constructs field for amount of exercises needed
    private void exerciseEntries(int i) {
        JLabel exerciseName = new JLabel("Exercise " + (i + 1) +  " name: ");
        JTextField exerciseNameField = new JTextField();
        fields.add(exerciseNameField);
        scrollPanel.add(exerciseName);
        scrollPanel.add(exerciseNameField);
        JLabel numSets = new JLabel("How many sets will be performed? ");
        JTextField numSetsField = new JTextField();
        fields.add(numSetsField);
        scrollPanel.add(numSets);
        scrollPanel.add(numSetsField);
    }

}
