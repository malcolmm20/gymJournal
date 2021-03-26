package ui.screens;

import model.GymJournal;
import model.OpenWorkout;
import ui.GymJournalGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckOneRepMaxesScreen extends Screen {

    private JList list;

    public CheckOneRepMaxesScreen(GymJournalGUI gui, GymJournal gj) {
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
        HashMap<String, Double> ormMap = gj.getOneRepMaxes();
        String[] oneRepMaxArray = new String[ormMap.size()];
        int i = 0;
        for (Map.Entry<String, Double> entry : ormMap.entrySet()) {
            oneRepMaxArray[i] = entry.getKey() + ": " + entry.getValue() + "lbs";
            i++;
        }
        list = new JList(oneRepMaxArray);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
    }
}
