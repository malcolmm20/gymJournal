package ui.tools;

import ui.GymJournalGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckOneRepMaxTool {
    private GymJournalGUI gui;

    private void oneRepMaxInterface() {
    }

    private class CheckOneRepMaxToolClickHandler implements ActionListener {

        // EFFECTS: calls oneRepMaxInterface when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            oneRepMaxInterface();
        }
    }


}
