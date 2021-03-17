package ui.tools;

import ui.GymJournalGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewWorkoutHistoryTool {
    private GymJournalGUI gui;

    private class AddWorkoutToolClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the PlayShape tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.setActiveTool(PlayShapeTool.this);
        }
    }
}
