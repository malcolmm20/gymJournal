package ui.tools;

import ui.GymJournalGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRoutineTool {
    private GymJournalGUI gui;

    private void addRoutineInterface() {
    }

    private class AddRoutineToolClickHandler implements ActionListener {

        // EFFECTS: calls addRoutineInterface when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            addRoutineInterface();
        }


    }
}
