package ui.screens;

import model.*;
import ui.GymJournalGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// screen where users can add a workout following an existing routine
public class AddRoutineWorkoutInterface extends InputInterface {

    private final ArrayList<JTextField> fields;
    private Routine routine;
    private JButton enter;
    private JButton clear;
    private JPanel scrollPanel;

    public AddRoutineWorkoutInterface(GymJournalGUI gui, GymJournal gj) {
        super(gui, gj);
        fields = new ArrayList<>();
        routine = new Routine("");
        scrollPanel = new JPanel();
    }

    // MODIFIES: this
    // EFFECTS: constructs form for user entry
    public void updateForm(Routine routine) {
        int s = fields.size();
        for (int i = 0; i < s; i++) {
            fields.remove(i);
        }
        northPanel.removeAll();
        scrollPanel.removeAll();
        northPanel.revalidate();
        scrollPanel.revalidate();
        this.routine = routine;
        scrollPanel.setLayout(new GridLayout(numRows() * 2 + 1, 2));
        for (RoutineExercise routineExercise : routine.getExercises()) {
            for (int j = 0; j < routineExercise.getSets(); j++) {
                exerciseEntries(j, routineExercise);
            }
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

    // EFFECTS: calculates the number of rows required in GridLayout
    private int numRows() {
        int numRows = 0;
        for (RoutineExercise routineExercise : routine.getExercises()) {
            numRows += routineExercise.getSets();
        }
        return numRows;
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
            OpenWorkout workout = new OpenWorkout();
            int i = 0;
            for (RoutineExercise routineExercise : routine.getExercises()) {
                WorkoutExercise exercise = new WorkoutExercise(routineExercise.getName());
                for (int j = 0; j < routineExercise.getSets(); j++) {
                    WorkoutSet set = new WorkoutSet(Integer.parseInt(fields.get(i).getText()),
                            Integer.parseInt(fields.get(i + 1).getText()));
                    exercise.addSet(set);
                    i += 2;
                }
                workout.addExercise(exercise);
            }
            gj.addWorkout(workout);
            clearSelection();
        }
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

    // EFFECTS: returns false if a non integer is in an integer field
    private boolean testNonIntegers() {
        try {
            for (JTextField field : fields) {
                Integer.parseInt(field.getText());
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    // MODIFIES: this
    // EFFECTS: creates JLabels and text fields for each set of each exercise
    private void exerciseEntries(int j, RoutineExercise exercise) {
        JLabel howManyReps = new JLabel("How many reps performed for set "
                + (j + 1) + " of " + exercise.getName());
        JTextField howManyRepsField = new JTextField();
        fields.add(howManyRepsField);
        scrollPanel.add(howManyReps);
        scrollPanel.add(howManyRepsField);
        JLabel howMuchWeight = new JLabel("What weight used for set " + (j + 1) + " of " + exercise.getName());
        JTextField howMuchWeightField = new JTextField();
        fields.add(howMuchWeightField);
        scrollPanel.add(howMuchWeight);
        scrollPanel.add(howMuchWeightField);
    }
}
