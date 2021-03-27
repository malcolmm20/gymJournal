package ui.screens;

import model.GymJournal;
import model.OpenWorkout;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.GymJournalGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class ViewWorkoutHistoryScreen extends Screen {
    private JLabel text;
    private JList list;

    public ViewWorkoutHistoryScreen(GymJournalGUI gui, GymJournal gj) {
        super(gui, gj);
        makeList();
        makeScrollPane();
        makeJLabel();
    }

    private void makeScrollPane() {
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        add(listScroller, BorderLayout.WEST);
    }


    private void makeJLabel() {
        text = new JLabel();
        text.setMinimumSize(new Dimension(WIDTH / 2, HEIGHT));
        add(text, BorderLayout.EAST);
    }

    public void updateList() {
        List<OpenWorkout> workoutArrayList = gj.getWorkoutHistory();
        OpenWorkout[] workoutArray = new OpenWorkout[workoutArrayList.size()];
        for (int i = 0; i < workoutArrayList.size(); i++) {
            workoutArray[i] = workoutArrayList.get(i);
        }
        list.setListData(workoutArray);
        list.ensureIndexIsVisible(list.getModel().getSize());
    }

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

    private String paragraphText(String s) {
        String paragraph = "<html>".concat(s);
        paragraph.concat("</html>");
        paragraph = paragraph.replace(".", "<br/>");
        System.out.println(paragraph);
        return paragraph;
    }


}
