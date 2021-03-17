package ui.tools;

import ui.GymJournalGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckPersonalBestsTool {
    private GymJournalGUI gui;

    private class CheckPersonalBestsToolClickHandler implements ActionListener {

        // EFFECTS: calls personalBestsInterface when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            personalBestsInterface();
        }
    }
}
