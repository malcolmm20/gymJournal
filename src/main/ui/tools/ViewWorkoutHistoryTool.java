package ui.tools;

import ui.MenuInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewWorkoutHistoryTool extends Tool {

    public ViewWorkoutHistoryTool(MenuInterface gui, JComponent parent) {
        super(gui, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("View Workout History");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        new ViewWorkoutHistoryToolClickHandler();
    }

    private class ViewWorkoutHistoryToolClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the PlayShape tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            viewWorkoutHistory();
        }
    }

    private void viewWorkoutHistory() {
    }
}
