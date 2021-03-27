package ui.screens;

import model.GymJournal;
import model.OpenWorkout;
import model.WorkoutSet;
import ui.GymJournalGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckPersonalBestsScreen extends Screen {

    private JList list;

    public CheckPersonalBestsScreen(GymJournalGUI gui, GymJournal gj) {
        super(gui, gj);
        makeList();
        makeScrollPane();
    }

    private void makeScrollPane() {
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(listScroller, BorderLayout.WEST);
    }

    public void makeList() {
        HashMap<String, WorkoutSet> pbMap = gj.getPersonalBests();
        String[] personalBestArray = new String[pbMap.size()];
        int i = 0;
        for (Map.Entry<String, WorkoutSet> entry : pbMap.entrySet()) {
            personalBestArray[i] = entry.getKey() + ": " + entry.getValue().getReps() + " reps, "
                    + entry.getValue().getReps() + " lbs";
            i++;
        }
        list = new JList(personalBestArray);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateList();
            }
        });
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
    }

    public void updateList() {
        HashMap<String, WorkoutSet> pbMap = gj.getPersonalBests();
        String[] personalBestArray = new String[pbMap.size()];
        int i = 0;
        for (Map.Entry<String, WorkoutSet> entry : pbMap.entrySet()) {
            personalBestArray[i] = entry.getKey() + ": " + entry.getValue().getReps() + " reps, "
                    + entry.getValue().getReps() + " lbs";
            i++;
        }
        list.setListData(personalBestArray);
        list.ensureIndexIsVisible(list.getModel().getSize());
    }
}

