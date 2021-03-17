package ui.tools;

import ui.GymJournalGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWorkoutTool {
    private GymJournalGUI gui;

    private void addWorkoutInterface() {
    }

    private class AddWorkoutToolClickHandler implements ActionListener {

        // EFFECTS: calls addWorkoutInterface when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            addWorkoutInterface();
        }
    }


}
