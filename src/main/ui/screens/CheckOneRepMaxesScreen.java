package ui.screens;

import model.GymJournal;
import model.OpenWorkout;
import ui.GymJournalGUI;
import ui.screens.renderers.ListRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// screen to check one rep maxes
public class CheckOneRepMaxesScreen extends Screen {

    private JList list;

    public CheckOneRepMaxesScreen(GymJournalGUI gui, GymJournal gj) {
        super(gui, gj);
        makeList();
        makeScrollPane();
    }

    // MODIFIES: this
    // EFFECTS: constructs scroll pane to navigate list
    private void makeScrollPane() {
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(listScroller, BorderLayout.WEST);
    }

    // EFFECTS: constructs JList of one rep maxes
    public void makeList() {
        HashMap<String, Double> ormMap = gj.getOneRepMaxes();
        String[] oneRepMaxArray = new String[ormMap.size()];
        int i = 0;
        for (Map.Entry<String, Double> entry : ormMap.entrySet()) {
            oneRepMaxArray[i] = entry.getKey() + ": " + entry.getValue() + "lbs";
            i++;
        }
        list = new JList(oneRepMaxArray);
        list.setCellRenderer(new ListRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
    }

    // EFFECTS: updates JList
    public void updateList() {
        HashMap<String, Double> ormMap = gj.getOneRepMaxes();
        String[] oneRepMaxArray = new String[ormMap.size()];
        int i = 0;
        for (Map.Entry<String, Double> entry : ormMap.entrySet()) {
            oneRepMaxArray[i] = entry.getKey() + ": " + entry.getValue() + "lbs";
            i++;
        }
        list.setListData(oneRepMaxArray);
        list.ensureIndexIsVisible(list.getModel().getSize());
    }
}
