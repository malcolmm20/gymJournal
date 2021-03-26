package ui.screens;

import model.GymJournal;
import model.OpenWorkout;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.GymJournalGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class ViewWorkoutHistoryScreen extends Screen {

    private JList list;

    public ViewWorkoutHistoryScreen(GymJournalGUI gui, GymJournal gj) {
        super(gui, gj);
        updateList();
        makeScrollPane();
    }

    private void makeScrollPane() {
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        add(listScroller, BorderLayout.WEST);
    }

    public void updateList() {
        List<OpenWorkout> workoutArrayList = gj.getWorkoutHistory();
        OpenWorkout[] workoutArray = new OpenWorkout[workoutArrayList.size()];
        for (int i = 0; i < workoutArrayList.size(); i++) {
            workoutArray[i] = workoutArrayList.get(i);
        }
        list = new JList(workoutArray);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
    }


}
