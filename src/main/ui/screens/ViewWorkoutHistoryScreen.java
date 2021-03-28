package ui.screens;

import model.GymJournal;
import model.OpenWorkout;
import ui.GymJournalGUI;
import ui.screens.renderers.WorkoutRenderer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

// jpanel that displays list of past workouts
public class ViewWorkoutHistoryScreen extends Screen {
    private JLabel text;
    private JList list;

    public ViewWorkoutHistoryScreen(GymJournalGUI gui, GymJournal gj) {
        super(gui, gj);
        makeList();
        makeScrollPane();
        makeJLabel();
    }

    // MODIFIES: this
    // EFFECTS: makes scroll pane for JList
    private void makeScrollPane() {
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        add(listScroller, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: makes JLabel to display toString of selected workout
    private void makeJLabel() {
        text = new JLabel();
        text.setFont(new Font("", Font.PLAIN, 20));
        text.setHorizontalAlignment(SwingConstants.LEFT);
        text.setVerticalAlignment(SwingConstants.NORTH);
        text.setMinimumSize(new Dimension(WIDTH / 2, HEIGHT));
        add(text, BorderLayout.CENTER);
    }

    // EFFECTS: update JList of workouts
    public void updateList() {
        List<OpenWorkout> workoutArrayList = gj.getWorkoutHistory();
        OpenWorkout[] workoutArray = new OpenWorkout[workoutArrayList.size()];
        for (int i = 0; i < workoutArrayList.size(); i++) {
            workoutArray[i] = workoutArrayList.get(i);
        }
        list.setListData(workoutArray);
        list.ensureIndexIsVisible(list.getModel().getSize());
    }

    // MODIFIES: this
    // EFFECTS: makes JList of workouts
    public void makeList() {
        List<OpenWorkout> workoutArrayList = gj.getWorkoutHistory();
        OpenWorkout[] workoutArray = new OpenWorkout[workoutArrayList.size()];
        for (int i = 0; i < workoutArrayList.size(); i++) {
            workoutArray[i] = workoutArrayList.get(i);
        }
        list = new JList(workoutArray);
        list.setCellRenderer(new WorkoutRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (list.getSelectedValue() != null) {
                    text.setText(paragraphText(list.getSelectedValue().toString()));
                }
            }
        });
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
    }

    // EFFECTS: changes workout toString method so it has HTML line breaks
    private String paragraphText(String s) {
        String paragraph = "<html>".concat(s);
        paragraph.concat("</html>");
        paragraph = paragraph.replace(".", "<br/>");
        return paragraph;
    }


}
