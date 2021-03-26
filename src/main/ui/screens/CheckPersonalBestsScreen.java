package ui.screens;

import model.GymJournal;
import model.OpenWorkout;
import model.WorkoutSet;
import ui.GymJournalGUI;

import javax.swing.*;
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
        updateList();
        makeScrollPane();
    }

    private void makeScrollPane() {
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        add(listScroller, BorderLayout.WEST);
    }

    public void updateList() {
        HashMap<String, WorkoutSet> pbMap = gj.getPersonalBests();
        String[] oneRepMaxArray = new String[pbMap.size()];
        int i = 0;
        for (Map.Entry<String, WorkoutSet> entry : pbMap.entrySet()) {
            oneRepMaxArray[i] = entry.getKey() + ": " + entry.getValue().getReps() + " reps, "
                    + entry.getValue().getReps() + " lbs";
            i++;
        }
        list = new JList(oneRepMaxArray);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
    }
}

