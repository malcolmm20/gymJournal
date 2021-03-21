package ui.tools;

import ui.MenuInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckPersonalBestsTool extends Tool {

    public CheckPersonalBestsTool(MenuInterface gui, JComponent parent) {
        super(gui, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Check Personal Bests");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        new CheckPersonalBestsToolClickHandler();
    }

    private class CheckPersonalBestsToolClickHandler implements ActionListener {

        // EFFECTS: calls personalBestsInterface when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            personalBestsInterface();
        }
    }

    private void personalBestsInterface() {
    }
}
